package com.techmonad.pipeline.util

import org.apache.spark.{SparkConf, SparkContext}


trait SparkContextProvider {

  val conf = new SparkConf().setMaster("local[*]").setAppName("DataPipeline")
  implicit val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")


}
