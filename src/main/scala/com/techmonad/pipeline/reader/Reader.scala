package com.techmonad.pipeline.reader

import org.apache.spark.sql.{DataFrame, SparkSession}


class Reader {

  def readCSV(url: String, delimiter: Char)(implicit spark: SparkSession): DataFrame = {
    spark.read.format("csv")
      .option("header", "true")
      .option("delimiter", s"$delimiter")
      .load(url)

  }

  def readJSON() = {

  }


}
