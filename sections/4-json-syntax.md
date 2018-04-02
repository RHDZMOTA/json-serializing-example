# Interfaces and extensions: Json Syntax

We can use extension methods (pimp) to extend types with an interface.

In this case we define the JsonSyntax as following:

```scala
package json

object JsonSyntax {

  implicit class JsonWriterOps[A](value: A){
    def toJson(implicit jsWriter: JsWriter[A]): JsValue =
      jsWriter write value
  }
}
```

We can use the interface syntax by importing it alongside the instances of the writers:

```scala
import java.util.Date
import JsWriterInstances._
import JsonSyntax._

Person("a", "a@mail.com", new Date()).toJson
```

Furthermore, we can enhance the writer instances:

```scala
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
```