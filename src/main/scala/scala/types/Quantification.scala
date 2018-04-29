package scala.types

object Quantification {

  // Sometimes you do not care to be able to name a type variable
  // For example: A type does not need to be named as no argument is of type A
  def count[A](l: List[A]) = l.size

  // Instead you can use “wildcards”:
  def count(l: List[_]) = l.size

  // This is shorthand for:
  def count(l: List[T forSome { type T }]) = l.size

  // Note that quantification can get tricky:
  def drop1(l: List[_]) = l.tail
  //  drop1: (List[_])List[Any]
//  Suddenly we lost type information! To see what’s going on, revert to the heavy-handed syntax:

  def drop1(l: List[T forSome { type T }]) = l.tail
//  drop1: (List[T forSome { type T }])List[T forSome { type T }]
//  We can’t say anything about T because the type does not allow it.


  //  You may also apply bounds to wildcard type variables:
  def hashcodes(l: Seq[_ <: AnyRef]) = l map (_.hashCode)
//  hashcodes: (Seq[_ <: AnyRef])Seq[Int]

  hashcodes(Seq(1,2,3))
//  <console>:7: error: type mismatch;
//    found   : Int(1)
//    required: AnyRef
//    Note: primitive types are not implicitly converted to AnyRef.
//    You can safely force boxing by casting x.asInstanceOf[AnyRef].
//    hashcodes(Seq(1,2,3))
//    ^

  hashcodes(Seq("one", "two", "three"))
//    res1: Seq[Int] = List(110182, 115276, 110339486)

}
