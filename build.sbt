name := "spark-data-pipeline"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.1.0",
  "org.scalatest" %% "scalatest" % "3.0.1"
)
    