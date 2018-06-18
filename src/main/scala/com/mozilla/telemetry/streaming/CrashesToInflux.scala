/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.mozilla.telemetry.streaming

import com.mozilla.telemetry.heka.Message
import com.mozilla.telemetry.pings.CrashPing
import com.mozilla.telemetry.sinks.RawHttpSink
import com.mozilla.telemetry.streaming.StreamingJobBase.TelemetryKafkaTopic
import org.apache.spark.sql.types.{BinaryType, StructField, StructType}
import org.apache.spark.sql._
import org.rogach.scallop.ScallopOption

import scala.collection.immutable.ListMap

object CrashesToInflux extends StreamingJobBase {

  val kafkaCacheMaxCapacity = 1000

  val defaultChannels: List[String] = List[String]("release", "beta", "nightly")
  val defaultAppNames: List[String] = List[String]("Firefox", "Fennec")

  private[streaming] class Opts(args: Array[String]) extends BaseOpts(args) {
    val raiseOnError: ScallopOption[Boolean] = opt[Boolean](
      "raiseOnError",
      descr = "Whether the program should exit on a data processing error or not.")
    val failOnDataLoss: ScallopOption[Boolean] = opt[Boolean](
      "failOnDataLoss",
      descr = "Whether to fail the query when it’s possible that data is lost.")
    val url: ScallopOption[String] = opt[String](
      "url",
      descr = "Endpoint to send data to",
      required = true)
    val maxParallelRequests: ScallopOption[Int] = opt[Int](
      "maxParallelRequests",
      descr = "Max number of parallel requests in batch mode",
      required = false,
      default = Some(100))
    val acceptedChannels: ScallopOption[List[String]] = opt[List[String]](
      "acceptedChannels",
      descr = "Release channels to accept crashes from",
      required = false,
      default = Some(defaultChannels))
    val acceptedAppNames: ScallopOption[List[String]] = opt[List[String]](
      "acceptedAppNames",
      descr = "Applications to accept crashes from",
      required = false,
      default = Some(defaultAppNames))
    val measurementName: ScallopOption[String] = opt[String](
      "measurementName",
      descr = "Name of measurement in InfluxDB",
      required = true)

    conflicts(kafkaBroker, List(from, to, fileLimit, maxParallelRequests))

    verify()
  }

  def parsePing(message: Message, channels: List[String], appNames: List[String],
                measurementName: String): Array[String] = {
    val fields = message.fieldsAsMap

    if (!fields.get("docType").contains("crash")) {
      Array[String]()
    } else {
      val ping = CrashPing(message)
      val metadata = ping.meta

      if (!channels.contains(metadata.normalizedChannel) || !appNames.contains(metadata.appName)) {
        Array[String]()
      } else {
        val timestamp = metadata.Timestamp

        val influxFields = ListMap(
          "buildId" -> ping.getNormalizedBuildId.getOrElse(metadata.appBuildId)
        )

        val influxTags = ListMap(
          "submissionDate" -> metadata.submissionDate,
          "appVersion" -> metadata.appVersion,
          "appName" -> metadata.appName,
          "displayVersion" -> ping.getDisplayVersion.getOrElse(""),
          "channel" -> metadata.normalizedChannel,
          "country" -> metadata.geoCountry,
          "osName" -> metadata.os.getOrElse(""),
          "osVersion" -> ping.getOsVersion.getOrElse(""),
          "architecture" -> ping.getArchitecture.getOrElse("")
        )

        val outputString = measurementName +
          influxTags.map { case (k, v) => s"$k=$v" }.mkString(",", ",", "") +
          influxFields.map { case (k, v) => s"$k=$v" }.mkString(" ", ",", "") +
          " " +
          timestamp

        Array[String](outputString)
      }
    }
  }

  def getParsedPings(pings: DataFrame, raiseOnError: Boolean, measurementName: String,
                     channels: List[String] = defaultChannels,
                     appNames: List[String] = defaultAppNames): Dataset[String] = {
    import pings.sparkSession.implicits._

    pings.flatMap( v => {
      try {
        parsePing(Message.parseFrom(v.get(0).asInstanceOf[Array[Byte]]), channels, appNames, measurementName)
      } catch {
        case _: Throwable if !raiseOnError => Array[String]()
      }
    }).as[String]
  }

  def sendStreamCrashes(spark: SparkSession, opts: Opts): Unit = {
    val httpSink = new RawHttpSink(opts.url(), Map())

    val pings = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", opts.kafkaBroker())
      .option("failOnDataLoss", opts.failOnDataLoss())
      .option("kafka.max.partition.fetch.bytes", 8 * 1024 * 1024) // 8MB
      .option("spark.streaming.kafka.consumer.cache.maxCapacity", kafkaCacheMaxCapacity)
      .option("subscribe", TelemetryKafkaTopic)
      .option("startingOffsets", opts.startingOffsets())
      .load()

    getParsedPings(pings.select("value"), opts.raiseOnError(),
      opts.measurementName(), opts.acceptedChannels(), opts.acceptedAppNames())
      .writeStream
      .queryName(QueryName)
      .foreach(httpSink)
      .start()
      .awaitTermination()
  }

  def sendBatchCrashes(spark: SparkSession, opts: Opts): Unit = {
    val maxParallelRequests = opts.maxParallelRequests()

    implicit val sc = spark.sparkContext

    datesBetween(opts.from(), opts.to.get).foreach { currentDate =>
      val dataset = com.mozilla.telemetry.heka.Dataset("telemetry")
      val pings = dataset
        .where("sourceName") {
          case "telemetry" => true
        }.where("docType") {
          case doctype if doctype == "crash" => true
        }.where("appUpdateChannel") { // TODO: make channel filtering work
          case appUpdateChannel if List[String]("beta").contains(appUpdateChannel) => true
        }.where("appName") { // TODO: make app filtering work
          case appName if List[String]("Firefox").contains(appName) => true
        }.where("submissionDate") {
          case date if date == currentDate => true
        }.records(opts.fileLimit.get).map(m => Row(m.toByteArray))

      val schema = StructType(List(
        StructField("value", BinaryType, nullable = true)
      ))

      val pingsDataFrame = spark.createDataFrame(pings, schema)
      val url = opts.url()

      getParsedPings(pingsDataFrame, opts.raiseOnError(),
        opts.measurementName(), opts.acceptedChannels(), opts.acceptedAppNames())
        .repartition(maxParallelRequests)
        .foreachPartition{ it: Iterator[String] =>
          val httpSink = new RawHttpSink(url, Map())
          val data = it.mkString("\n")
          httpSink.process(data)
        }
    }
    if (!spark.conf.get("spark.home").startsWith("/databricks")) {
      spark.stop()
    }
  }

  def process(opts: Opts): Unit = {
    val spark = SparkSession.builder()
      .appName("CrashesToInflux")
      .getOrCreate()

    opts.kafkaBroker.get match {
      case Some(_) => sendStreamCrashes(spark, opts)
      case None => sendBatchCrashes(spark, opts)
    }
  }

  def main(args: Array[String]): Unit = {
    val opts = new Opts(args)
    process(opts)
  }
}
