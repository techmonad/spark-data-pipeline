package com.techmonad.pipeline.reader

import com.techmonad.pipeline.util.Status
import com.techmonad.pipeline.{Record, SparkSupport}
import org.scalatest.{Matchers, WordSpec}


class ReaderSpec extends WordSpec with Matchers with SparkSupport {


  "Reader " should {
    "read csv " in withSparkContext { implicit spark =>
      val csvData: Array[Record] = CSVReader.read("""src/test/resources/csv/tweet.csv""", ',').collect()
      csvData.length shouldBe 832
      csvData.filter(_.status != Status.ERROR).length shouldBe 832
      csvData.filter(_.status == Status.OK).foreach(println)


    }

  }
}
