package com.techmonad.pipeline


import com.techmonad.pipeline.util.{JsonHelper, SparkContextProvider}
import com.techmonad.pipeline.workflow.WorkFlow

object RunDataPipeline extends App with SparkContextProvider with JsonHelper {

  val workFlowJson =
    """
      |{
      |  "source": {
      |    "type": "CSV",
      |    "path": "ipl-tweet.csv",
      |    "meta":{"text_field":"text","date_field": "date","author_field":"author_name" }
      |  },
      |
      |  "validations": [ "COLUMN_VALIDATION",  "FIELD_VALIDATION"  ],
      |
      |  "transformations": ["SENTIMENT_ANALYSIS"  ],
      |
      |  "schemaValidations": [    ],
      |
      |  "sink": {
      |    "type": "ES",
      |    "meta":{ "index": "data_index","type": "twitter"    }
      |  }
      |}
    """.stripMargin


  /*    if (args.length < 1)
        throw new IllegalArgumentException("Data directory and workflow json are required")
      else
        args(0)*/

  val workFlow = parse(workFlowJson).extract[WorkFlow]

  DataPipeline(workFlow).run

  sc.stop()

}

