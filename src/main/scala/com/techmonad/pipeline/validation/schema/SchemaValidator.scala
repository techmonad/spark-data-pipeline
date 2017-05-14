package com.techmonad.pipeline.validation.schema

import com.techmonad.pipeline.Record
import org.apache.spark.rdd.RDD


object SchemaValidator {

  implicit class SchemaValidator(val self: RDD[Record]) extends AnyVal {

    def validateSchema(): RDD[Record] = {
      self.mapPartitions { records =>
        records.map {
          SchemaValidation.validate
        }
      }
    }
  }

}
