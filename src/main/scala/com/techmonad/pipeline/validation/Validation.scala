package com.techmonad.pipeline.validation

import com.techmonad.pipeline.Record


trait Validation {

  def name: String

  def validate(record: Record): Record

}
