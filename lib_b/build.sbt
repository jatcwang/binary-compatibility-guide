name := "lib-b"

organization := "com.example"

version := "1.1.0-SNAPSHOT"

val defaultScalaVersion = "2.12.3"

crossScalaVersions := Seq(defaultScalaVersion, "2.11.11")

libraryDependencies ++= Seq(
    "com.example" %% "lib-common" % "2.0.0-SNAPSHOT"
)
