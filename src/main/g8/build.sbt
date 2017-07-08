organization := "com.example"

name := "$name$"

version := "$version$"

scalaVersion := "$scala_version$"

val unfilteredVersion = "$unfiltered_version$"

libraryDependencies ++= Seq(
  "ws.unfiltered" %% "unfiltered-filter" % unfilteredVersion,
  "ws.unfiltered" %% "unfiltered-jetty" % unfilteredVersion,
  "ws.unfiltered" %% "unfiltered-specs2" % unfilteredVersion % "test",
  "org.scalatra.scalate" %% "scalate-core" % "$scalate_version$"
)
