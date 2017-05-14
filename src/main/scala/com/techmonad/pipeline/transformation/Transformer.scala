package com.techmonad.pipeline.transformation

import com.techmonad.pipeline.Record
import com.techmonad.pipeline.transformation.sentiment.SentimentAnalyzer
import org.apache.spark.rdd.RDD


object Transformer {

  implicit class Transformer(val self: RDD[Record]) extends AnyVal {

    def transform() = {
      self.mapPartitions { records =>
        records.map {
          SentimentAnalyzer.transform
        }
      }
    }
  }

}
