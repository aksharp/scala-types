package scala.types

object TypeInference {

  //  A traditional objection to static typing is that it has much syntactic overhead.
  //    Scala alleviates this by providing type inference.
  //    Scala’s type inference system infers constraints, and attempt to unify a type.
  //      (type unification will be discussed in detail in future sessions)

//    In scala all type inference is local. Scala considers one expression at a time.

//    For example:

  def id[T](x: T) = x
  //  id: [T](x: T)T

  val x = id(322)
  //  x: Int = 322

  val y = id("hey")
  //  y: java.lang.String = hey

  val z = id(Array(1,2,3,4))
  //  z: Array[Int] = Array(1, 2, 3, 4)

  //  Types are now preserved,
  //    The Scala compiler infers the type parameter for us.
  //    Note also how we did not have to specify the return type explicitly.

}
