name := "app"

organization := "com.example"

val defaultScalaVersion = "2.12.3"

crossScalaVersions := Seq(defaultScalaVersion, "2.11.11")

libraryDependencies ++= Seq(
  "com.example" %% "lib-common" % "2.0.0-SNAPSHOT",
  "com.example" %% "lib-a" % "1.0.0-SNAPSHOT",
  "com.example" %% "lib-b" % "1.1.0-SNAPSHOT",

  "org.scalatest" %% "scalatest" % "3.0.4",
)
