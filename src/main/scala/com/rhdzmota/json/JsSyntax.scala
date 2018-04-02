package com.rhdzmota.json

object JsSyntax {

  implicit class JsWriterOps[A](value: A){
    def toJson(implicit jsWriter: JsWriter[A]): JsValue =
      jsWriter write value
  }
}
