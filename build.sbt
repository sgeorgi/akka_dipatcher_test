organization := "de.sgeorgi"

name := "tamon-hub"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "io.spray"            %   "spray-can_2.11"     % "1.3.1",
  "io.spray"            %   "spray-routing_2.11" % "1.3.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "io.spray"            %   "spray-testkit_2.11" % "1.3.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)

Revolver.settings
