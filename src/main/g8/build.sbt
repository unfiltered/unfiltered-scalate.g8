organization := "com.example"

name := "$name$"

version := "$version$"

scalaVersion := "$scala_version$"

val unusedWarnings = (
  "-Ywarn-unused" ::
  Nil
)

libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "always"

scalacOptions ++= PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion.value)){
  case Some((2, v)) if v >= 11 => unusedWarnings
}.toList.flatten

Seq(Compile, Test).flatMap(c =>
  c / console / scalacOptions --= unusedWarnings
)

scalacOptions ++= "-deprecation" :: "unchecked" :: "-feature" :: Nil

val unfilteredVersion = "$unfiltered_version$"

libraryDependencies ++= Seq(
  "ws.unfiltered" %% "unfiltered-filter" % unfilteredVersion,
  "ws.unfiltered" %% "unfiltered-jetty" % unfilteredVersion,
  "ws.unfiltered" %% "unfiltered-specs2" % unfilteredVersion % "test",
  "org.scalatra.scalate" %% "scalate-core" % "$scalate_version$"
)
