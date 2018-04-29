package scala.types

object Bounds {

  //  Scala allows you to restrict polymorphic variables using bounds.
  //    These bounds express subtype relationships.

  // Bounds
  // A <: B   means A is a subtype of B
  // A >: B   means A is a supertype of B
  // A : B    means A is B (requires implicit evidence to support this claim)

  // reuse our class hierarchy
  class Animal { val sound = "rustle" }
  class Bird extends Animal { override val sound = "call" }
  class Chicken extends Bird { override val sound = "cluck" }

  def cacophony[T](things: Seq[T]) = things map (_.sound)
  //error: value sound is not a member of type parameter T

  def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)

  biophony(Seq(new Chicken, new Bird))
  // res: Seq[java.lang.String] = List(cluck, call)



  // The new List has the same type as the original:
  val flock = List(new Bird, new Bird)
  // res: flock: List[Bird] = List(Bird@7e1ec70e, Bird@169ea8d2)

  // List defines an operator ::(elem T) that returns a new List with elem prepended.
  new Chicken :: flock
  // res: List[Bird] = List(Chicken@56fbda05, Bird@7e1ec70e, Bird@169ea8d2)

  // List[+T] is covariant; a list of Birds is a list of Animals.
  //  List prepend operator :: is defined ::[B >: T](x: B) which returns a List[B].
  //    Notice the B >: T. That specifies type B as a superclass of T.
  //    That lets us do the right thing when prepending an Animal to a List[Bird]:
  new Animal :: flock
  // List[Animal] = List(Animal@11f8d3a8, Bird@7e1ec70e, Bird@169ea8d2)
  //  Note that the return type is List[Animal]


  def lesser[A : Ordering](a: A, b: A)(implicit ev: Ordering[A]) =
    if ( ev.lt(a, b) )
      a
    else
      b

}
