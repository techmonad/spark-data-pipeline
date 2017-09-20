package com.techmonad.pipeline

import com.techmonad.pipeline.persist.ESPersistence._
import com.techmonad.pipeline.transformation.Transformer._
import com.techmonad.pipeline.util.SparkContextProvider
import com.techmonad.pipeline.validation._

object RunDataPipeline extends App with SparkContextProvider {

  import schema.SchemaValidator._
  import source.SourceValidator._

  val (dataDir, workFlowJson) =
    if (args.length < 2)
      throw new IllegalArgumentException("Data directory and workflow json are required")
    else
      (args(0), args(1))


  DataPipeline(dataDir)
    .read
    .validate
    .transform
    .validateSchema
    .saveToES


  //DataPipeline(dataDir)
  // .create(workFlow)
  // .run
}

case class WorkFlow(
                     source: Source,
                     validations: List[String],
                     transformations: List[String],
                     schemaValidation: List[String],
                     sink: Sink
                   )

case class Source(`type`:String, dir:String, meta:Map[String,String])

case class Sink()

case class Validation()

case class Transformation()

case class SchemaValidation()

/*
"""
|{
 |  "source": {
 |    "type": "CSV",
 |    "dir": "s3://data/bucket_name",
 |    "meta_info":{"text_field":"text","date_field": "date","author_field":"author_name" }
 |  },
 |
 |  "validation": [    "COLUMN_VALIDATION",  "FIELD_VALIDATION"  ],
 |
 |  "transformation": [    "SENTIMENT_ANALYSIS"  ],
 |
 |  "schemaValidation": [    "DATA_MODEL_VALIDATION"  ],
 |
 |  "sink": {
 |    "storage_type": "ES",
 |    "meta_info":{ "index": "data_index","type": "twitter"    }
 |  },
 |
 |  "postProcessing": [    "SEND_EMAIL_NOTIFICATION"  ]
 |}
"""
*/