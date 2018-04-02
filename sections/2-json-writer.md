# Type Classes: Json Writer

A type class is an interface that represents functionality we would like to implement.
We can define a JsonWriter type class as following:

```scala
trait JsWriter[A] {
  def write(value: A): JsValue
}
```

This type-class is defined with a generic value **A**. This allows us to implement the write method
according to arbitrary data-types. 