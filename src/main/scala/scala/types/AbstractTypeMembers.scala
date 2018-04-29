package scala.types

import scala.language.higherKinds

object AbstractTypeMembers {

//  In a trait, you can leave type members abstract.

  trait Foo {
    type A

    val x: A

    def getX: A = x
  }

  new Foo {
    type A = Int
    val x = 123
  }.getX // = 123


  new Foo {
    type A = String
    val x = "hey"
  }.getX // = "hey"

//  This is often a useful trick when doing dependency injection, etc.

//  You can refer to an abstract type variable using the hash-operator:
  trait Bar[M[_]] {
    type Inner[A] = M[A]
  }

  val x: Bar[List]#Inner[Int] = List(1)
//  x: List[Int] = List(1)

  // more on how this can be useful when we talk about Aux pattern


}
