# Algebraic Data Types

Scala's traits are an abstraction over classes that allows us to model data (classes) that implement the same
operations (share a common super-type).

Traits are templates for creating classes in the same way that classes are
templates for creating objects.

To create a trait in Scala:
```scala
trait TraitName {
    // declaration or expression
}

class ClassName(???) extends TraitName {
    // implementation
}

```

A trait is thus a named set of fields and method definitions. Main differences with classes are:
* A trait cannot have a constructor.
* Traits can define abstract methods with type signatures but no implementation.

Furthermore, we can express any data model in Scala in terms of logical **or** and **and**s.

## Product Type Patters

## has-a and
**A** has a **B** and a **C**.

```scala
case class A(b: B, c: C)
```

Or

```scala
trait A {
  def b: B
  def c: C
}
```

## has-a or

**A** has a **d** of type **D** where **D** is a **B** or a **C**.

```scala
trait A {
  def d: D
}

sealed trait D
final case class B() extends D
final case class C() extends D
```

**A** is a **D** or **E** and **D** has **B** and **E** has **C**.

```scala
sealed trait A

final case class D(b: B) extends A
final case class E(c: C) extends A
```

## Sum Type Pattern

## is-a and

**A** is a **B** and **C**.

```scala
trait B
trait C

trait A extends B with C
```

## is-a or

**A** is a **B** or a **C**.

```scala
sealed trait A

final case class B()
final case class C()
```

## Summary

An algebraic datatype is any data that uses the sum-type pattern or the product-type pattern. 