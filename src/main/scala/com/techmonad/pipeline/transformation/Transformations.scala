package com.techmonad.pipeline.transformation

import com.techmonad.pipeline.transformation.sentiment.SentimentAnalyzer

object Transformations {

  private val transformations: Map[String, Transformation] = Map(
    SentimentAnalyzer.name -> SentimentAnalyzer
  )

  def get(name: String): Option[Transformation] = transformations.get(name)
}
