name := """knobs-example"""

organization := "acme"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

resolvers += Resolver.bintrayRepo("oncue", "releases")

libraryDependencies ++= Seq(
  "oncue.knobs" %% "core" % "3.2.1"
)

initialCommands := "import acme._"
