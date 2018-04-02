package json

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
