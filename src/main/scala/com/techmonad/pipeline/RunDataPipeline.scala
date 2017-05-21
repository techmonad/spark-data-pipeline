package com.techmonad.pipeline

import com.techmonad.pipeline.persist.ESPersistence._
import com.techmonad.pipeline.transformation.Transformer._
import com.techmonad.pipeline.util.SparkContextProvider
import com.techmonad.pipeline.validation._

object RunDataPipeline extends App with SparkContextProvider {

  import schema.SchemaValidator._
  import source.SourceValidator._

  val dataDir = "/home/satendra/open-source/spark-data-pipeline/src/test/resources/csv/tweet.csv"//args(0)

  DataPipeline(dataDir)
    .read
    .validate
    .transform
    .validateSchema
    .saveToES

}
