package json

import org.scalatest._

class JsValueSpec extends FlatSpec with Matchers {

  "The JsObject class" should "stringify {Hello: World}" in {
    JsObject(Map("Hello" -> JsString("World"))).stringify shouldEqual "{\"Hello\": \"World\"}"
  }



}
