package com.techmonad.pipeline

import com.techmonad.pipeline.util.Status


case class Record(data: Map[String, Any], status: Status.Value = Status.OK, reason: Option[String] = None)

