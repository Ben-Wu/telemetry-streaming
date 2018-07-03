/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.mozilla.telemetry.streaming

import com.mozilla.telemetry.heka.Message
import com.mozilla.telemetry.pings.{CrashFrame, CrashModule, CrashPing, StackTraces}
import com.mozilla.telemetry.sinks.RawHttpSink
import com.mozilla.telemetry.streaming.StreamingJobBase.TelemetryKafkaTopic
import org.apache.spark.sql.types.{BinaryType, StructField, StructType}
import org.apache.spark.sql._
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization
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
    val minDelay: ScallopOption[Long] = opt[Long](
      "minDelay",
      descr = "Amount of delay between requests in batch mode, in ms",
      required = false,
      default = Some(0))
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

    conflicts(kafkaBroker, List(from, to, fileLimit, minDelay, maxParallelRequests))

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

        if (ping.payload.stackTraces.values == None) {

        } else {
          implicit val formats = DefaultFormats
          val stackTrace = ping.payload.stackTraces.extract[StackTraces]

          val parsedStackTraces = parseStackTrace(stackTrace)
        }

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

    pings.flatMap( v =>
        parsePing(Message.parseFrom(v.get(0).asInstanceOf[Array[Byte]]), channels, appNames, measurementName)
    ).as[String]
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
    spark.stop()
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

  case class SymModule(debugFile: String, debugId: String)

  case class SymThread(index: BigInt, offset: BigInt, module: SymModule)

  def getFramesToSymbolicate(frame: CrashFrame, idx: Int, modules: List[CrashModule]): Array[SymThread] = {
    if (frame.ip.isEmpty) {
      throw new Exception("missing ip")
    }

    val ip = BigInt(frame.ip.get.substring(2), 16)

    val moduleIdx = frame.module_index.getOrElse(-1)
    if (moduleIdx > modules.length) {
      throw new Exception("module index out of range")
    } else if (moduleIdx >= 0) {
      val module = modules(moduleIdx)

      if (module.base_addr.isEmpty) {
        throw new Exception("base addr not in module")
      }

      val moduleOffset = ip - BigInt(module.base_addr.get.substring(2), 16)

      if (module.debug_file.isDefined && module.debug_id.isDefined) {
        val symModule = SymModule(module.debug_file.get, module.debug_id.get)
        Array[SymThread](SymThread(idx, moduleOffset, symModule))
      } else {
        Array[SymThread]()
      }
    } else {
      Array[SymThread]()
    }
  }

  def getThreadsToSymbolicate(frames: List[CrashFrame], modules: List[CrashModule], threadIdx: Int,
                              crashingThread: Int): Unit = {
    if (threadIdx == 0 || threadIdx == crashingThread) {
      val threadsToSymbolicate = frames.zipWithIndex.flatMap({
        case (frame, i) => getFramesToSymbolicate(frame, i, modules)
      })

      val stacks = threadsToSymbolicate.map(t => List[BigInt](t.index, t.offset))
      val memoryMap = threadsToSymbolicate.map(t => List[String](t.module.debugFile, t.module.debugId))

      implicit val formats = org.json4s.DefaultFormats

      // TODO: dedup

      val requestBody = Map[String, Any](
        "stacks" -> stacks,
        "memoryMap" -> memoryMap,
        "version" -> 5
      )

      println(Serialization.write(requestBody))
    }
  }

  def parseStackTrace(stackTraces: StackTraces): String = {
    if (stackTraces.threads.isEmpty || stackTraces.modules.isEmpty
      || stackTraces.crash_info.isEmpty) {
      throw new Exception("'threads', 'modules', and 'crash_info' must be in stack trace")
    }

    if (stackTraces.crash_info.get.crashing_thread.isEmpty) {
      throw new Exception("'crashing_thread' must be in crash_info")
    }

    val crashingThread = stackTraces.crash_info.get.crashing_thread.get
    val frameList = stackTraces.threads.get.map(_("frames"))
    val modules = stackTraces.modules.get

    if (crashingThread < 0 || crashingThread >= frameList.length) {
      throw new Exception("crashing thread out of range")
    }

    getThreadsToSymbolicate(frameList(0), modules, 0, 0)
    ""
  }
}
