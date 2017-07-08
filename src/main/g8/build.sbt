organization := "com.example"

name := "$name$"

version := "$version$"

scalaVersion := "$scala_version$"

libraryDependencies ++= Seq(
  "ws.unfiltered" %% "unfiltered-filter" % "$unfiltered_version$",
  "ws.unfiltered" %% "unfiltered-jetty" % "$unfiltered_version$",
  "org.scalatra.scalate" %% "scalate-core" % "$scalate_version$"
)
