package com.techmonad.pipeline.transformation.sentiment

import java.util.Properties

import com.techmonad.pipeline.Record
import com.techmonad.pipeline.transformation.Transformation
import com.techmonad.pipeline.util.Status
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations

import scala.collection.convert.wrapAll._


class SentimentAnalyzer extends Transformation {

  override def transform(record: Record): Record =
    if (record.status != Status.ERROR) {
      val sentiment: String = SentimentAnalyzer.getSentiment(record.data("text").toString)
      record.copy(data = record.data + ("sentiment" -> sentiment))
    } else {
      record
    }


}

object SentimentAnalyzer {

  private val props = new Properties()
  props.setProperty("annotators", "tokenize, ssplit, parse, sentiment")
  private val pipeline: StanfordCoreNLP = new StanfordCoreNLP(props)

  def getSentiment(input: String): String = {
    val annotation: Annotation = pipeline.process(input)
    val (_, sentiment) =
      annotation.get(classOf[CoreAnnotations.SentencesAnnotation])
        .map { sentence => (sentence, sentence.get(classOf[SentimentCoreAnnotations.SentimentAnnotatedTree])) }
        .map { case (sentence, tree) => (sentence.toString, getSentimentType(RNNCoreAnnotations.getPredictedClass(tree))) }
        .maxBy { case (sentence, _) => sentence.length }
    sentiment
  }


  private def getSentimentType(sentiment: Int): String = sentiment match {
    case x if x == 3 || x == 4 => "positive"
    case x if x == 0 || x == 1 => "negative"
    case 2 => "neutral"
  }
}
