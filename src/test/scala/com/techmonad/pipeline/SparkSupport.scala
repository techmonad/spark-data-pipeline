package com.techmonad.pipeline

import org.apache.commons.lang.RandomStringUtils
import org.apache.spark.sql.SparkSession

import scala.util.control.NonFatal


trait SparkSupport {

  def withSparkSession(spec: (SparkSession => Unit)) = {
    val spark: SparkSession =
      SparkSession
        .builder()
        .appName("SparkSupport- " + RandomStringUtils.random(20))
        .config("spark.master", "local[1]")
        .config("spark.default.parallelism", "1")
        .getOrCreate()
    try {
      spec(spark)
    } catch {
      case NonFatal(th) =>

        spark.stop()
        throw th

    }
  }

}
