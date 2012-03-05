organization := "com.example"

name := "$name$"

version := "$version$"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "$unfiltered_version$",
  "net.databinder" %% "unfiltered-jetty" % "$unfiltered_version$",
  "org.fusesource.scalate" % "scalate-core" % "1.5.3",
  "org.clapper" %% "avsl" % "0.3.6"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2"
)
