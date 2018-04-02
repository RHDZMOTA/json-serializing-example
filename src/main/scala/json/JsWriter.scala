package json

import website.{Visitor, Anonymous, User}

trait JsWriter[A] {

  def write(value: A): JsValue
}
