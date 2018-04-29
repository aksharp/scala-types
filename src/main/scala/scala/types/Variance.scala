package scala.types

object Variance {

  /*
  Scala’s type system has to account for class hierarchies together with polymorphism.
    Class hierarchies allow the expression of subtype relationships.
    A central question that comes up when mixing OO with polymorphism is:
      if T’ is a subclass of T,
        is Container[T’] considered a subclass of Container[T]?
    Variance annotations allow you to express the following relationships between class hierarchies & polymorphic types:
  */

  /*
  Scala notation:

  [+T] = covariant	C[T’] is a subclass of C[T]
  [-T] = contravariant	C[T] is a subclass of C[T’]
  [T]  = invariant	C[T] and C[T’] are not related

  The subtype relationship really means: for a given type T, if T’ is a subtype, can you substitute it?
  */

  class Covariant[+A]

  val goodCovariantRelationship: Covariant[AnyRef] = new Covariant[String]
  val goodContainerCovariantRelationship: List[Covariant[AnyRef]] = List(new Covariant[String])

  val wrongCovariantRelationship: Covariant[String] = new Covariant[AnyRef]
  val wrongContainerCovariantRelationship: List[Covariant[String]] = List(new Covariant[AnyRef])


  class Contravariant[-A]

  val goodContravariantRelationship: Contravariant[String] = new Contravariant[AnyRef]
  val goodContainerContravariantRelationship: Vector[Contravariant[String]] = Vector(new Contravariant[AnyRef])

  val wrongContravariantRelationship: Contravariant[AnyRef] = new Contravariant[String]
  val wrongContainerContravariantRelationship: List[Contravariant[AnyRef]] = List(new Contravariant[String])


  class Invariant[A]

  val goodInvariantRelationship: Invariant[String] = new Invariant[String]
  val goodContainerInvariantRelationship: Option[Invariant[String]] = Option(new Invariant[String])

  val wrongInvariantRelationship: Invariant[String] = new Invariant[AnyRef]
  val wrongContainerInvariantRelationship: List[Invariant[String]] = List(new Invariant[AnyRef])



  // Contravariance seems strange. When is it used?
  // Scala Standard Library Function1
  trait Function1 [-T1, +R] extends AnyRef {
    def apply(v1: T1): R
  }

  // Function1 from the point of view of substitution makes a lot of sense.

  // Let’s first define a simple class hierarchy:
  // Animal -> Bird -> Chicken
  class Animal { val sound = "rustle" }
  class Bird extends Animal { override val sound = "call" }
  class Chicken extends Bird { override val sound = "cluck" }

  //Suppose you need a function that takes a Bird param:
  val getTweet: (Bird => String) = ??? //TODO implement
  // let's say there's a function that does what you want, but it takes Animal parameter
  // In most situations, if you say “I need a ___, I have a subclass of ___”, you’re OK.
  // But function parameters are contravariant.

  // use Animal => String function to implement
  val getTweetImpl: (Bird => String) =
    (a: Animal) => a.sound

  // A function’s return value type is covariant.
  // If you need a function that returns a Bird but have a function that returns a Chicken, that’s great.

  // use () => Chicken
  val hatch: (() => Bird) =
    () => new Chicken

}
