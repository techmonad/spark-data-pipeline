package com.techmonad.pipeline.transformation

import com.techmonad.pipeline.Record


trait Transformation {

  def transform(record: Record): Record
}
