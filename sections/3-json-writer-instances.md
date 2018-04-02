# Type Class Instances: Json Writer Instances

We can implement the type-class named JsonWriter for each relevant data-type.

Assume we are working with these data models: 
```scala
import java.util.Date

sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime - createdAt.getTime
}

final case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

final case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

```

Then, the instances for the JsonWriter would be:
```scala
import website.{Visitor, Anonymous, User}

trait JsWriter[A] {
  def write(value: A): JsValue
}

object JsWriterInstances {
  import java.util.Date


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
      "createdAt" -> JsString(value.createdAt.toString)
    ))
  }

  implicit case object UserWriter extends JsWriter[User] {
    override def write(value: User): JsValue = JsObject(Map(
      "id" -> value.id.toJson,
      "email" -> JsString(value.email),
      "createdAt" -> JsString(value.createdAt.toString)
    ))
  }

 
}
```

As it can be seen, we have a writer for sime data types (string, double and date) and for the
website data types (anonymous, user).

If we want to use the writers we only have to import:

```scala
import java.util.Date
import JsWriterInstances._ 

JsonUtil.toJson(Person("a", "a@mail.com", new Date()))
```
