package com.techmonad.pipeline.validation.schema

import com.techmonad.pipeline.Record
import com.techmonad.pipeline.util.Status
import com.techmonad.pipeline.validation.Validation


object SchemaValidation extends Validation {

  import Schema._

  override def validate(record: Record): Record =
    if (record.status != Status.ERROR) {
      val missingFields = fields.collect { case field if !(record.data.contains(field)) => field }
      if (missingFields.isEmpty && record.data.size == fields.length)
        record
      else
        record.copy(status = Status.ERROR, reason = Some(s"Fields ${missingFields.mkString} are missing"))
    } else {
      record
    }


}
