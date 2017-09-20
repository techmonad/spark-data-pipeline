package com.techmonad.pipeline.util


import org.json4s._
import org.json4s.native.JsonMethods.{parse => jParser}
import org.json4s.native.Serialization.{write => jWrite}

trait JsonHelper {

  implicit val formats = DefaultFormats

  protected def write[T <: AnyRef](value: T): String = jWrite(value)

  protected def parse(value: String): JValue = jParser(value)

}