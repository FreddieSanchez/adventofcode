name := "adventofcode2019"
organization := ""
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.9"

lazy val EnumeratumVersion = "1.5.13"
libraryDependencies := Seq(
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.beachape"               %% "enumeratum"               % EnumeratumVersion,
)


