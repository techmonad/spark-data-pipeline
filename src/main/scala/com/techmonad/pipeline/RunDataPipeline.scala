package com.techmonad.pipeline


import com.techmonad.pipeline.util.{JsonHelper, SparkContextProvider}
import com.techmonad.pipeline.workflow.WorkFlow

object RunDataPipeline extends App with SparkContextProvider with JsonHelper {

  val workFlowJson =
    if (args.length < 1)
      throw new IllegalArgumentException("Data directory and workflow json are required")
    else
      (args(0))


  val workFlow = parse(workFlowJson).extract[WorkFlow]

  DataPipeline(workFlow).run
}

