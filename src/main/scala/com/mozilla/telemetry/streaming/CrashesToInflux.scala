/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.mozilla.telemetry.streaming

import com.mozilla.telemetry.heka.Message
import com.mozilla.telemetry.pings._
import com.mozilla.telemetry.sinks.RawHttpSink
import com.mozilla.telemetry.streaming.StreamingJobBase.TelemetryKafkaTopic
import org.apache.spark.sql.types.{BinaryType, StructField, StructType}
import org.apache.spark.sql._
import org.json4s.DefaultFormats
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.rogach.scallop.ScallopOption

import scala.collection.immutable.ListMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.language.postfixOps


import sys.process._

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
    val getCrashSignature: ScallopOption[Boolean] = opt[Boolean](
      "getCrashSignature",
      descr = "Use symbolication api and siggen library to get crash signature",
      required = false,
      default = Some(false)
    )

    conflicts(kafkaBroker, List(from, to, fileLimit, maxParallelRequests))

    verify()
  }

  def parsePing(message: Message, channels: List[String], appNames: List[String],
                measurementName: String, usingDatabricks: Boolean, getSignature: Boolean): Array[String] = {
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

        val crashSignature = if (getSignature) getCrashSignature(ping.payload, usingDatabricks) else ""

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
          "architecture" -> ping.getArchitecture.getOrElse(""),
          "processType" -> ping.payload.processType.getOrElse("main")
        ).filter{case (k, v) => v.nonEmpty}

        val outputString = measurementName +
          influxTags.map { case (k, v) => s"$k=$v" }.mkString(",", ",", "") +
          (if (crashSignature.nonEmpty) s",crashSignature=$crashSignature" else "")  +
          influxFields.map { case (k, v) => s"$k=$v" }.mkString(" ", ",", " ") +
          timestamp

        Array[String](outputString)
      }
    }
  }

  def getParsedPings(pings: DataFrame, raiseOnError: Boolean, measurementName: String,
                     channels: List[String] = defaultChannels,
                     appNames: List[String] = defaultAppNames,
                     usingDatabricks: Boolean = false,
                     getSignature: Boolean = false): Dataset[String] = {
    import pings.sparkSession.implicits._

    pings.flatMap( v =>
      try {
        parsePing(Message.parseFrom(v.get(0).asInstanceOf[Array[Byte]]),
          channels, appNames, measurementName, usingDatabricks, getSignature)
      } catch {
        case _: Throwable if !raiseOnError => Array[String]()
      }
    ).as[String]
  }

  def sendStreamCrashes(spark: SparkSession, opts: Opts): Unit = {
    val httpSink = new RawHttpSink(opts.url(), Map())

    val usingDatabricks = spark.conf.getOption("spark.home").getOrElse("").startsWith("/databricks")

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
      opts.measurementName(), opts.acceptedChannels(), opts.acceptedAppNames(),
      usingDatabricks, opts.getCrashSignature())
      .writeStream
      .queryName(QueryName)
      .foreach(httpSink)
      .start()
      .awaitTermination()
  }

  def sendBatchCrashes(spark: SparkSession, opts: Opts): Unit = {
    val maxParallelRequests = opts.maxParallelRequests()

    implicit val sc = spark.sparkContext

    val usingDatabricks = spark.conf.getOption("spark.home").getOrElse("").startsWith("/databricks")

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
        opts.measurementName(), opts.acceptedChannels(), opts.acceptedAppNames(),
        usingDatabricks, opts.getCrashSignature())
        .repartition(maxParallelRequests)
        .foreachPartition{ it: Iterator[String] =>
          val httpSink = new RawHttpSink(url, Map())
          val data = it.mkString("\n")
          httpSink.process(data)
        }
    }

    if (usingDatabricks) {
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

  // TODO: move to util class

  case class SymbolicatedReponse(results: List[SymbolicatedResult])

  case class SymbolicatedResult(stacks: List[JValue])

  case class CrashSignature(notes: List[String], proto_signature: String, signature: String)

  def getCrashSignature(payload: CrashPayload, usingDatabricks: Boolean): String = {
    if (payload.stackTraces.values == None) {
      ""
    } else {
      try {
        implicit val formats = DefaultFormats
        val stackTrace = payload.stackTraces.extract[StackTraces]

        val parsedStackTraces = parseStackTrace(stackTrace)

        val httpSink = new RawHttpSink("https://symbols.mozilla.org/symbolicate/v5", Map())

        val response = httpSink.processWithResponse(Serialization.write(parsedStackTraces))

        if (response.isEmpty) {
          ""
        } else {
          val responseSerialized = parse(response).extract[SymbolicatedReponse]

          val crashingThread = stackTrace.crash_info.get.crashing_thread.getOrElse(0)

          val signifyBody = Map[String, Any](
            "crashing_thread" -> crashingThread,
            "threads" -> List(Map("frames" -> responseSerialized.results.head.stacks.head))
          )

          val commandPath = if (usingDatabricks) "/databricks/python/bin/signify" else "signify"
          val result = s"echo ${Serialization.write(signifyBody)}" #| commandPath !!

          val crashSignature = parse(result).extract[CrashSignature]
          crashSignature.signature.replace(" ", "\\ ")
        }
      } catch {
        case e: Throwable => "" // TODO: Don't use base Exception
      }
    }
  }

  case class SymFrame(offset: BigInt, debugFile: String, debugId: String)

  def createSymbolicationFrame(frame: CrashFrame, modules: List[CrashModule]): Array[SymFrame] = {
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
        Array[SymFrame](SymFrame(moduleOffset, module.debug_file.get, module.debug_id.get))
      } else {
        Array[SymFrame]()
      }
    } else {
      Array[SymFrame]()
    }
  }

  def getFramesToSymbolicate(frames: List[CrashFrame], modules: List[CrashModule], threadIdx: Int,
                             crashingThread: Int): List[SymFrame] = {
    if (threadIdx == 0 || threadIdx == crashingThread) {
      frames.flatMap(createSymbolicationFrame(_, modules))
    } else {
      List[SymFrame]()
    }
  }

  def parseStackTrace(stackTraces: StackTraces): Map[String, Any] = {
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

    val framesToSymbolicate = frameList.flatMap(
      getFramesToSymbolicate(_, modules, 0, 0)
    )

    // TODO: can we do this with no side effects
    // stacks is a list of tuples of (index of associated item in memoryMap, module offset)
    val stacks = ListBuffer[List[Any]]()
    // memoryMap is list of tuples of (debug file, debug id)
    val memoryMap = ListBuffer[List[String]]()

    val indexMap = mutable.HashMap[String, Int]()

    for (frame <- framesToSymbolicate) {
      if (!indexMap.contains(frame.debugId)) {
        memoryMap.append(List[String](frame.debugFile, frame.debugId))
      }
      val index = indexMap.getOrElseUpdate(frame.debugId, memoryMap.length - 1)
      stacks.append(List(index, frame.offset))
    }

    Map[String, Any](
      "stacks" -> List(stacks), // list of stacks need to be wrapped in a list
      "memoryMap" -> memoryMap,
      "version" -> 5
    )
  }
}
