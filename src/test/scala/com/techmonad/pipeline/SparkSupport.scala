package com.techmonad.pipeline

import org.apache.commons.lang.RandomStringUtils
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.control.NonFatal


trait SparkSupport {

  def withSparkContext(spec: (SparkContext => Unit)) = {
    val conf = new SparkConf().setAppName("SparkSupport- " + RandomStringUtils.random(20))
      .setMaster("local[4]")
      .set("spark.default.parallelism", "1")
    val sc: SparkContext = new SparkContext(conf)
    try
      spec(sc)
    catch {
      case NonFatal(th) =>
        sc.stop()
        throw th

    }
  }

}
