package scala.types

import com.sun.tools.javap.TypeAnnotationWriter.Note

object HigherKindedTypes {

//  Scala can abstract over “higher kinded” types. For example, suppose that you needed to use several types of containers for several types of data.
  // You might define a Container interface that might be implemented by means of several container types:
  //  an Option, a List, etc. You want to define an interface for using values in these containers without nailing down the values’ type.


  trait Container[M[_]] {
    def put[A](x: A): M[A]
    def get[A](m: M[A]): A
  }

  val container = new Container[List] {
    def put[A](x: A) = List(x)
    def get[A](m: List[A]) = m.head
  }

  container.put("hey")

  container.put(123)

//  Note that Container is polymorphic in a parameterized type (“container type”).

}
