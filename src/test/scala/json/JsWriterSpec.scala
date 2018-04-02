package json

import java.util.Date
import org.scalatest._

import json.Implicits._
import website.{Anonymous, Visitor, User}

class JsWriterSpec extends FlatSpec with Matchers {

  "The JsWriter implicit objects" should "stringify a sequence of Visitors" in {
    val visitors: Seq[Visitor] = Seq(Anonymous("001", new Date), User("003", "dave@example.com", new Date))
    visitors.map(JsUtil.toJson(_).stringify)
  }

}
