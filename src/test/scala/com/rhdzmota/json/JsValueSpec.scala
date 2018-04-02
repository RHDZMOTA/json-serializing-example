package com.rhdzmota.json

import org.scalatest._

class JsValueSpec extends FlatSpec with Matchers {

  "JsNull object" should "stringify to null" in {
    val jsNull = JsNull
    jsNull.stringify shouldBe "null"
  }

  "JsString class" should "stringify instance JsString(Hey) to \"Hey\"" in {
    val jsString = JsString("Hey")
    jsString.stringify shouldBe "\"Hey\""
  }

  "JsNumber class" should "stringify instance JsNumber(5) to \"5\"" in {
    val jsNumber = JsNumber(5)
    jsNumber.stringify shouldBe "5.0"
  }

  "JsObject class" should "stringify instance JsObject(Map(Hello -> JsString(World))) to {\"Hello\": \"World\"}" in {
    val jsObject = JsObject(Map("Hello" -> JsString("World")))
    jsObject.stringify shouldBe "{\"Hello\": \"World\"}"
  }

}

