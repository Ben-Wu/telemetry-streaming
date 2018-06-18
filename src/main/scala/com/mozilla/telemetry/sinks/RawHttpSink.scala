/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.mozilla.telemetry.sinks

import com.mozilla.telemetry.streaming.sinks.HttpSink
import scalaj.http.{Http, HttpRequest}

import scala.annotation.tailrec
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

// TODO: Remove and use generalized HttpSink instead
class RawHttpSink[String](url: String, data: Map[String, String], maxAttempts: Int = 5, defaultDelay: Int = 500, connectionTimeout: Int = 2000)
  extends HttpSink[String](url, data, maxAttempts, defaultDelay, connectionTimeout) {

  override def process(event: String): Unit = {
    attempt(
      Http(url.toString)
        .postData(event.toString)
        .timeout(connTimeoutMs = connectionTimeout, readTimeoutMs = ReadTimeout))
  }

  private def backoff(tries: Int): Long = (scala.math.pow(2, tries) - 1).toLong * defaultDelay

  @tailrec
  private def attempt(request: HttpRequest, tries: Int = 0): Unit = {
    if(tries > 0) { // minor optimization
      java.lang.Thread.sleep(backoff(tries))
    }

    val code = Try(request.asString.code) match {
      case Success(c) => c
      case Failure(e: java.net.SocketTimeoutException) => TimeoutPseudoCode
      case Failure(e) if NonFatal(e) => {
        log.error(e.getStackTrace.mkString("\n"))
        ErrorPseudoCode
      }
    }

    (code, tries + 1 == maxAttempts) match {
      case (OK, _) =>
      case (ErrorPseudoCode, _) =>
      case (c, false) if RetryCodes.contains(c) => attempt(request, tries + 1)
      case (c, _) => {
        val url = request.url + "?" + request.params.map{ case(k, v) => s"$k=$v" }.mkString("&")
        log.warn(s"Failed request: $url, last status code: $c")
      }
    }
  }
}
