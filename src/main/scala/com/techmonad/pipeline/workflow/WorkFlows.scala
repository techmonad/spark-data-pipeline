package com.techmonad.pipeline.workflow

case class WorkFlow(
                     source: Source,
                     validations: List[String],
                     transformations: List[String],
                     schemaValidations: List[String],
                     sink: Sink
                   )

case class Source(`type`: String, path: String, meta: Map[String, String])

case class Sink(`type`: String, meta: Map[String, String])


/*
"""
|{
 |  "source": {
 |    "type": "CSV",
 |    "path": "/home/satendra/decooda/testing-csv",
 |    "meta":{"text_field":"text","date_field": "date","author_field":"author_name" }
 |  },
 |
 |  "validation": [    "COLUMN_VALIDATION",  "FIELD_VALIDATION"  ],
 |
 |  "transformation": [    "SENTIMENT_ANALYSIS"  ],
 |
 |  "schemaValidation": [    "DATA_MODEL_VALIDATION"  ],
 |
 |  "sink": {
 |    "type": "ES",
 |    "meta":{ "index": "data_index","type": "twitter"    }
 |  }
 |}
"""
*/
