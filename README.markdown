A [giter8][g8] template for [Unfiltered][unfiltered] applications with Scalate, Jetty.

[g8]: https://github.com/foundweekends/giter8#readme
[unfiltered]: https://github.com/unfiltered/unfiltered#readme


    g8 unfiltered/unfiltered-scalate

The instructions below refer to the default Scalate interface created
in this template project.

--------------------------------------------------------------------------------------

# Unfiltered Scalate Integration

## Getting Started

Integrating [Scalate](https://scalate.github.io/scalate/) templates into your Unfiltered app is dead simple with the Unfiltered Scalate Module.  Here is a quick and dirty example.

```scala
import unfiltered.request._
import unfiltered.response._
import unfiltered.jetty._

object Server{
  def main(args: Array[String]): Unit = {
    val binding = SocketPortBinding(host = "localhost", port = 8080)
    val server = unfiltered.jetty.Server.portBinding(binding).plan(unfiltered.filter.Planify {
      case req => Ok ~> Scalate(req, "hello.ssp")
    }).run
  }
}
```

and the template looks like, and must be placed in src/main/resources/templates:

    <%@ var name: String = "Deron Williams" %>
    <%@ var city: String = "Salt Lake City" %>
    <h1>Hello, ${name} from ${city}</h1>

To replace the name and city variables with your own, you pass tuples of (String, Any) in the Scalate object

    Scalate(req, "hello.ssp", ("name", "Dustin"), ("city", "Paris"))

Next you probably want to serve static assets, like images, css, and javascript files.  To do that, using Jetty as an example, you need to setup a context, and the code would look something like:

```scala
object Server{
  def main(args: Array[String]): Unit = {
    val binding = SocketPortBinding(host = "localhost", port = 8080)
    val server = unfiltered.jetty.Server.portBinding(binding)
    server.context("/public"){ ctx =>
      ctx.resources(new java.net.URL("file:///Users/molecule/development/scalate_demo/src/main/resources/public"))
    }.plan(unfiltered.filter.Planify {
      case req => Ok ~> Scalate(req, "hello.ssp")
    }).run
  }
}
```

now you can reference static assets from your template like /public/main.css or /public/images/logo.png

...and that's about it!

## Further Configuration and Usage

### Custom Template Engine
If you wish to move your templates somewhere else, or you want to configure the org.fusesource.scalate.TemplateEngine for production use, you can create your own TemplateEngine and pass it to the secondary parameters set manually:

```scala
import unfiltered.response._
import unfiltered.jetty._
import org.fusesource.scalate.TemplateEngine

object Server{
  def main(args: Array[String]): Unit = {
    val templateDirs = List(new java.io.File("/my/own/template/dirs"))
    val scalateMode = "production"
    val engine = new TemplateEngine(templateDirs, scalateMode)
    
    val binding = SocketPortBinding(host = "localhost", port = 8080)
    val server = unfiltered.jetty.Server.portBinding(binding)
    server.context("/public"){ ctx =>
      ctx.resources(new java.net.URL("file:///Users/molecule/development/scalate_demo/src/main/resources/public"))
    }.plan(unfiltered.filter.Planify {
      case req => Ok ~> Scalate(req, "hello.ssp")(engine)
    }).run
  }
}
```

or the TemplateEngine can be implicit:

```scala
implicit val engine = new TemplateEngine(templateDirs, scalateMode)

val binding = SocketPortBinding(host = "localhost", port = 8080)
val server = unfiltered.jetty.Server.portBinding(binding)
server.context("/public"){ ctx =>
  ctx.resources(new java.net.URL("file:///Users/molecule/development/scalate_demo/src/main/resources/public"))
}.plan(unfiltered.filter.Planify {
  case req => Ok ~> Scalate(req, "hello.ssp")
}).run
```
