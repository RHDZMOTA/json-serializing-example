package com.rhdzmota.json

import org.scalatest._

class JsSyntaxSpec extends FlatSpec with Matchers {

  import java.util.Date
  import JsWriterInstances._ 
  import JsSyntax._

  "Implicit StringWriter object" should "create Json for \"Hello\" using method extensions" in {
    "Hello".toJson shouldBe JsString("Hello")
  }

  "Implicit DoubleWriter object" should "create Json from input 5.0 using method extensions" in {
    5.0.toJson shouldBe JsNumber(5)
  }
}  
