package com.rhdzmota.json

import org.scalatest._

class JsWriterSpec extends FlatSpec with Matchers {

  import java.util.Date
  import JsWriterInstances._

  "Implicit StringWriter object" should "create Json from \"Hello\" string using JsonUtil" in {
    val result = JsUtil.toJson("Hello") 
    result.stringify shouldBe "\"Hello\"" 
  }

  "Implicit DoubleWriter object" should "create Json from input 5.0 using JsonUtil" in {
    JsUtil.toJson(5.0).stringify shouldBe "5.0"
  }

  "Implicit DateWriter object" should "create Json from a Date object using JsonUtil" in {
    JsUtil.toJson(new Date()).stringify
  }

}
