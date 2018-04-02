package json

import website.{Visitor, Anonymous, User}

trait JsWriter[A] {
  def write(value: A): JsValue
}

object JsWriterInstances {
  import java.util.Date
  import JsonSyntax.JsonWriterOps

  implicit object StringWriter extends JsWriter[String] {
    override def write(value: String): JsValue = JsString(value)
  }

  implicit object DoubleWriter extends JsWriter[Double] {
    override def write(value: Double): JsValue = JsNumber(value)
  }

  implicit object DateWriter extends JsWriter[Date] {
    override def write(value: Date): JsValue = JsString(value.toString)
  }

  // Website models

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