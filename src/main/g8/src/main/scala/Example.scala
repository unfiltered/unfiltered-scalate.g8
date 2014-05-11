package com.example

import unfiltered.request._
import unfiltered.response._

/** unfiltered plan */
class App extends unfiltered.filter.Plan {
  import QParams._

  def intent = {
    case req@GET(Path(p)) =>
      view(req, "body" -> "What say you?")
    case req@POST(Path(p) & Params(params)) =>
      val expected = for {
        int <- lookup("int") is
          int { _ + " is not an integer" } is
          required("missing int")
        word <- lookup("palindrome") is
          trimmed is
          nonempty("Palindrome is empty") is
          pred(palindrome, { _ + " is not a palindrome" }) is
          required("missing palindrome")
      } yield view(
        req,
        "body" -> "Yup. %d is an integer and %s is a palindrome".format(
          int.get, word.get
        )
      )
      expected(params) orFail { fails =>
        view(req, "errors" -> fails.map { f => Map("error" -> f.error) })
      }
  }
  def palindrome(s: String) = s.toLowerCase.reverse == s.toLowerCase
  def view[T](req: HttpRequest[T], extra: (String, Any)*) = {
    val Params(params) = req
    Scalate(req, "palindrome.mustache", (params.toSeq ++ extra): _*)
  }
}

/** embedded server */
object Server {
  def main(args: Array[String]) {
    val http = unfiltered.jetty.Http.anylocal
    http.context("/assets") { _.resources(
      new java.net.URL(getClass().getResource("/www/css"), ".")
    ) }.filter(new App).run({ svr =>
        unfiltered.util.Browser.open(http.url)
      }, { svr =>
      })
  }
}
