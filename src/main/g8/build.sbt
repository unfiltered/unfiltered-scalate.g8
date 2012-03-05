organization := "com.example"

name := "My Web Project"

version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.6.1",
  "net.databinder" %% "unfiltered-jetty" % "0.6.1",
  "org.fusesource.scalate" % "scalate-core" % "1.5.3",
  "org.clapper" %% "avsl" % "0.3.6"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2"
)
