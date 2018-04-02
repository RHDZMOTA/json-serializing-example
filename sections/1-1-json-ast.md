# Json Abstract Syntax Tree

In this case study we define a simple JSON AST with algebraic datatypes.


We first create a trait with an abstract method followed by a json object and 
concrete json values (string, double, null) 

```scala
sealed trait JsValue {
  def stringify: String
}

final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  override def stringify: String = values
    .map { case (name, value) => "\"" + name + "\": " + value.stringify }
    .mkString("{", ",", "}")
}

final case class JsString(value: String) extends JsValue {
  override def stringify: String = "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
}

final case class JsNumber(value: Double) extends JsValue {
  override def stringify: String =  "\"" + value.toString + "\""
}

case object JsNull extends JsValue {
  override def stringify: String = "null"
}
```

With this we can create JSON objects as following: 
```scala

val example: JsValue = JsObject(Map("Hello" -> JsString("World!"))
assert(example.stringify == "{\"Hello\": \"World!\"}"}
```


