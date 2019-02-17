package com.techmonad.pipeline.reader

import com.techmonad.pipeline.Record
import com.techmonad.pipeline.util.{CSVParser, Status, TryHelper}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD


object CSVReader extends TryHelper {

  def read(url: String, delimiter: Char = ',')(implicit sc: SparkContext): RDD[Record] = {
    val headers = sc.textFile(url).first()
    sc.textFile(url)
      .mapPartitionsWithIndex {
        case (index, itr) =>
          if (index == 0)
            readFile(itr.drop(1), headers)
          else
            readFile(itr, headers)
      }
  }


  private def readFile(itr: Iterator[String], firstLine: String): Iterator[Record] = {
    val parser = new CSVParser()
    val headers = parser.parse(firstLine)
    itr.map { line =>
      withTry(parser.parse(line)) match {
        case Some(row) =>
          if (row.length == headers.length)
            Record(headers.zip(row).toMap)
          else
            Record(Map("record" -> line), Status.ERROR, Some(s"Headers mismatch, actual length is [${headers.length}] but found: {${row.length}]"))
        case None =>
          Record(Map("record" -> line), Status.ERROR, Some("Invalid record"))
      }
    }
  }

}
