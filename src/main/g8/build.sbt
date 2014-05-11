organization := "com.example"

name := "$name$"

version := "$version$"

scalaVersion := "$scala_version$"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "$unfiltered_version$",
  "net.databinder" %% "unfiltered-jetty" % "$unfiltered_version$",
  "org.scalatra.scalate" %% "scalate-core" % "$scalate_version$"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2"
)
