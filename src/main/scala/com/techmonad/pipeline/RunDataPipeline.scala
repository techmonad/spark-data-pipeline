package com.techmonad.pipeline

import com.techmonad.pipeline.persist.ESPersistence._
import com.techmonad.pipeline.transformation.Transformer._
import com.techmonad.pipeline.util.SparkContextProvider
import com.techmonad.pipeline.validation._

object RunDataPipeline extends App with SparkContextProvider {

  import schema.SchemaValidator._
  import source.SourceValidator._

  val dataDir = args(0)

  DataPipeline(dataDir)
    .read
    .validate
    .transform
    .validateSchema
    .save


}
