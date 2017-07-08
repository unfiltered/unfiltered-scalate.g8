package com.example

import org.specs2.mutable.Specification
import okhttp3._

object ExampleSpec extends Specification with unfiltered.specs2.jetty.Served {

  override def setup() = { _.plan(new App) }

  private[this] val http = new OkHttpClient()

  "The example app" should {
    "serve unfiltered requests" in {
      val request = new Request.Builder().url(host).build()
      val response = http.newCall(request).execute()
      response.code must_== 200
      response.body.string must contain("What say you?")
    }
  }
}
