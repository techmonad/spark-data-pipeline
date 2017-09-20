package com.techmonad.pipeline.persist

import com.techmonad.pipeline.Record
import com.techmonad.pipeline.util.Status
import org.apache.spark.rdd.RDD
import org.elasticsearch.spark._

trait PersistenceRDD {

  protected val rdd: RDD[Record]

  def run(): Unit

}

class ESPersistenceRDD(protected val rdd: RDD[Record]) extends PersistenceRDD {

  def run(): Unit = {
    val validRecords = rdd.collect { case record if (record.status != Status.ERROR) => record.data }
    if (!validRecords.isEmpty())
      validRecords.saveToEs("data_index/twitter")
    val invalidRecords = rdd.collect { case record if (record.status == Status.ERROR) => record.data + ("reason" -> record.reason.getOrElse("")) }
    if (!invalidRecords.isEmpty())
      invalidRecords.saveToEs("invalid_data_index/twitter")
  }

}
