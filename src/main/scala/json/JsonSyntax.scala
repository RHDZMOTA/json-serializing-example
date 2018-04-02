package json

object JsonSyntax {

  implicit class JsonWriterOps[A](value: A){
    def toJson(implicit jsWriter: JsWriter[A]): JsValue =
      jsWriter write value
  }
}
