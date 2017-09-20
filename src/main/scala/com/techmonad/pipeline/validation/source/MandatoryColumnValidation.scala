package com.techmonad.pipeline.validation.source

import com.techmonad.pipeline.Record
import com.techmonad.pipeline.util.Status
import com.techmonad.pipeline.validation.Validation

object MandatoryColumnValidation extends Validation {


  override def name: String = "COLUMN_VALIDATION"

  override def validate(record: Record): Record =
    record.data.get("text") match {
      case Some(text: String) if (text.trim.nonEmpty) =>
        record.data.get("date") match {
          case Some(date: String) if (date.trim.nonEmpty) =>
            record
          case None =>
            record.copy(status = Status.ERROR, reason = Some("Date field should not be empty"))
        }
      case _ =>
        record.copy(status = Status.ERROR, reason = Some("Text field should not be empty"))
    }

}
