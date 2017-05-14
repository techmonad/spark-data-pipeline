package com.techmonad.pipeline.validation.source

import com.techmonad.pipeline.Record
import org.apache.spark.rdd.RDD


object SourceValidator {

  implicit class SourceValidator(val self: RDD[Record]) extends AnyVal {

    def validate(): RDD[Record] = {
      self.mapPartitions { records =>
        records.map {
          MandatoryColumnValidation.validate
        }
      }
    }
  }



}
