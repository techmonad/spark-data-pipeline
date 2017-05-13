package com.techmonad.pipeline.reader

import com.techmonad.pipeline.SparkSupport
import org.apache.spark.sql.Row
import org.scalatest.{Matchers, WordSpec}


class ReaderSpec extends WordSpec with Matchers with SparkSupport {

  val reader = new CSVReader()

  "Reader " should {
    "read csv " in withSparkSession { implicit spark =>
      val csvData: Array[Row] = reader.readCSV("""src/test/resources/csv/emp.csv""", ',').collect()
      csvData.length shouldBe 3
      csvData(0).get(0).toString.toInt shouldBe 1
      csvData(0).get(1).toString shouldBe "jon"

    }

  }
}
