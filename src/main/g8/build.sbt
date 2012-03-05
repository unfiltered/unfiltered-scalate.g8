organization := "com.example"

name := "$name$"

version := "$version$"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "$unfiltered_version$",
  "net.databinder" %% "unfiltered-jetty" % "$unfiltered_version$",
  // note: scalate 1.5.3 leaves sbt's run task hanging
  "org.fusesource.scalate" % "scalate-core" % "1.5.2",
  "org.clapper" %% "avsl" % "0.3.6"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2"
)
