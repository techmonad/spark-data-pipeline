package com.techmonad.pipeline

import com.techmonad.pipeline.reader.CSVReader
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD


class DataPipeline(dataDir: String) {


  def read(implicit sc: SparkContext): RDD[Record] = {
    CSVReader.read(dataDir)
  }

}

object DataPipeline {

  def apply(dataDir: String): DataPipeline = new DataPipeline(dataDir)

}
