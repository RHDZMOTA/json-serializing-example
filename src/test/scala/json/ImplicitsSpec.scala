package json

import org.scalatest._

import java.util.Date

class ImplicitsSpec extends FlatSpec with Matchers {

  "Implicits" should "stringify website models" in {
    import website.{Anonymous, User}
    import json.Implicits._
    val anon = Anonymous("001", new Date())
    val user = User("001", "0", new Date())

    anon.toJson.stringify
    user.toJson.stringify
  }

}
