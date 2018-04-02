package com.rhdzmota.json

object JsUtil {
  
  def toJson[A](value: A)(implicit jsWriter: JsWriter[A]): JsValue = 
    jsWriter write value
}

