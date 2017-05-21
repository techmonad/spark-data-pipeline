name := "spark-data-pipeline"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.0",
  "org.elasticsearch" %% "elasticsearch-spark-20" % "5.4.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0" artifacts(Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp")),
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.0.1"
)
