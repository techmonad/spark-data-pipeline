package com.techmonad.pipeline.transformation

import com.techmonad.pipeline.Record


trait Transformation {

  val name:String

  def transform(record: Record): Record
}
