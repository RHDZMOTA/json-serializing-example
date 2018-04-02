package json

import java.util.Date
import website.{Anonymous, User, Visitor}

object Implicits {

  implicit class JsUtil[A](value: A) {
    def toJson(implicit jsWriter: JsWriter[A]): JsValue =
      jsWriter write value
  }

  implicit object StringWriter extends JsWriter[String] {
    def write(value: String) = JsString(value)
  }

  implicit object DateWriter extends JsWriter[Date] {
    def write(value: Date) = JsString(value.toString)
  }

  implicit case object AnonymousWriter extends JsWriter[Anonymous] {
    override def write(value: Anonymous): JsValue = JsObject(Map(
      "id" -> value.id.toJson,
      "createdAt" -> value.createdAt.toJson
    ))
  }

  implicit case object UserWriter extends JsWriter[User] {
    override def write(value: User): JsValue = JsObject(Map(
      "id" -> value.id.toJson,
      "email" -> value.email.toJson,
      "createdAt" -> value.createdAt.toString.toJson
    ))
  }

  implicit object VisitorWriter extends JsWriter[Visitor] {
    override def write(value: Visitor): JsValue = value match {
      case anon: Anonymous => anon.toJson
      case user: User => user.toJson
    }
  }
}
