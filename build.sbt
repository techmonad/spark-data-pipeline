name := "spark-data-pipeline"

version := "1.0"

scalaVersion := "2.11.12"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "com.univocity" % "univocity-parsers" % "2.6.4",
  "org.elasticsearch" %% "elasticsearch-spark-20" % "6.6.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0" artifacts(Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp")),
  "ch.qos.logback" % "logback-classic" % "1.2.10",
  "org.json4s" %% "json4s-native" % "3.5.4",
  "org.scalatest" %% "scalatest" % "3.0.1"
)
