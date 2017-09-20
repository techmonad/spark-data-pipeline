package com.techmonad.pipeline.validation

import com.techmonad.pipeline.validation.source.MandatoryColumnValidation

object Validations {

  private val validations:Map[String, Validation]= Map(
    MandatoryColumnValidation.name -> MandatoryColumnValidation
  )

  def get(name:String): Option[Validation] = validations.get(name)

}
