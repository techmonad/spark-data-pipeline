package com.techmonad.pipeline

import com.techmonad.pipeline.util.SparkContextProvider


object RunDataPipeline extends App with SparkContextProvider {

  val dataDir = args(0)

  DataPipeline(dataDir)
    .read


}
