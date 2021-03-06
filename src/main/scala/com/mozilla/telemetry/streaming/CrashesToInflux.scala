/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.mozilla.telemetry.streaming

import com.mozilla.telemetry.sinks.BatchHttpSink

object CrashesToInflux extends CrashPingStreamingBase {

  override val sparkAppName: String = this.getClass.getSimpleName

  override def buildOutputString(measurementName: String, timestamp: Long,
                                 buildId: String, tags: Map[String, String]): String = {
    measurementName +
      tags.map { case (k, v) => s"$k=$v" }.mkString(",", ",", " ") +
      s"buildId=$buildId" +
      " " +
      timestamp
  }

  override def getHttpSink(url: String, maxBatchSize: Int): BatchHttpSink = {
    new BatchHttpSink(url, maxBatchSize = maxBatchSize, successCode = 204)
  }

  // special characters from:
  // https://docs.influxdata.com/influxdb/v1.6/write_protocols/line_protocol_tutorial/#special-characters-and-keywords
  override def formatCrashSignature(signature: String): String = {
    signature
      .replace(" ", "\\ ")
      .replace(",", "\\,")
      .replace("=", "\\=")
      .replace("\"", "\\\"")
  }
}
