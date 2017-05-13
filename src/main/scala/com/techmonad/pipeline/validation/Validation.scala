package com.techmonad.pipeline.validation

import com.techmonad.pipeline.Record


trait Validation {

  def validate(record: Record): Record

}
