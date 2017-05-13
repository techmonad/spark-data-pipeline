package com.techmonad.pipeline.persist

import com.techmonad.pipeline.Record
import com.techmonad.pipeline.util.Status
import org.apache.spark.rdd.RDD
import org.elasticsearch.spark._

object ESPersistence {

  implicit class ESPersistence(val self: RDD[Record]) extends AnyVal {
    def save(): Unit = {
      val validRecords = self.filter(_.status != Status.ERROR)
      if (!validRecords.isEmpty())
        validRecords.saveToEs("data_index/twitter")
      val invalidRecords = self.filter(_.status == Status.ERROR)
      if (!invalidRecords.isEmpty())
        invalidRecords.saveToEs("invalid_data_index/twitter")
    }
  }

}
