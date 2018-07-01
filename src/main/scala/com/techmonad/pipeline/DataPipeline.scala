package com.techmonad.pipeline

import com.techmonad.pipeline.persist.ESPersistenceRDD
import com.techmonad.pipeline.reader.CSVReader
import com.techmonad.pipeline.transformation.Transformations
import com.techmonad.pipeline.validation.Validations
import com.techmonad.pipeline.workflow.{Sink, Source, WorkFlow}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.util.{Failure, Success, Try}

trait DataPipeline {

  def run(): Unit
}


object DataPipeline {

  def apply(workFlow: WorkFlow)(implicit sc: SparkContext): DataPipeline = {
    Try {
      val sourceRDD = applySource(workFlow.source)
      val validatedRDD = applyValidation(sourceRDD, workFlow.validations)
      val transformedRDD = applyTransformation(validatedRDD, workFlow.transformations)
      val schemaValidatedRDD = applySchemaValidation(transformedRDD, workFlow.schemaValidations)
      applySink(schemaValidatedRDD, workFlow.sink)
    } match {
      case Success(sink) =>
        new DataPipeline {
          override def run(): Unit = sink.run()
        }
      case Failure(th) =>
        println("Error: Invalid workflow " + workFlow) //TODO: use logger
        throw th
    }
  }

  private def applySource(source: Source)(implicit sc: SparkContext): RDD[Record] = {
    CSVReader.read(source.path)
  }

  private def applyValidation(rdd: RDD[Record], validations: List[String]): RDD[Record] =
    validations match {
      case Nil => rdd
      case head :: tail =>
        applyValidation(
          Validations.get(head).map { v => rdd.map(v.validate) }.getOrElse(rdd)
          , tail)
    }


  private def applyTransformation(rdd: RDD[Record], transformations: List[String]): RDD[Record] =
    transformations match {
      case Nil => rdd
      case head :: tail =>
        applyTransformation(Transformations.get(head).map { v => rdd.map(v.transform) }.getOrElse(rdd), tail)
    }

  private def applySchemaValidation(rdd: RDD[Record], validations: List[String]): RDD[Record] = {
    applyValidation(rdd, validations)
  }

  private def applySink(rdd: RDD[Record], sink: Sink): ESPersistenceRDD =
    sink.`type` match {
      case "ES" => new ESPersistenceRDD(rdd)
    }

}
