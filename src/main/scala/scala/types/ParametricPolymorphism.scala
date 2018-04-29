package scala.types

object ParametricPolymorphism {

  val question = "What is parametric polymorphism?"

  val answer = "Polymorphism is used in order to write generic code (for values of different types) without compromising static typing richness"

  val list = 2 :: 1 :: "bar" :: "foo" :: Nil
  // List[Any] = List(2, 1, bar, foo)

  list.head
  // can't recover type information

  def drop1[A](l: List[A]) = l.tail

  // drop1: [A](l: List[A])List[A]

  val result = drop1(List(1, 2, 3))
  //result: List[Int] = List(2, 3)

  // Scala has rank-1 polymorphism
  //  Roughly, this means that there are some type concepts you’d like to express in Scala
  //    that are “too generic” for the compiler to understand. Suppose you had some function
  def toList[A](a: A) = List(a)
  // which you wished to use generically:
//  def foo[A, B](f: A => List[A], b: B) = f(b)
  // this works
  def foo[A](f: A => List[A], a: A) = f(a)

  // This does not compile,
  //  because all type variables have to be fixed at the invocation site.
  //    Even if you “nail down” type B,
  // You get a type mismatch
//  def foo[A](f: A => List[A], i: Int) = f(i)




}