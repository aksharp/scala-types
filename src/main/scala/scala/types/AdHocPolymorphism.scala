package scala.types

import scala.language.higherKinds

object AdHocPolymorphism {

//  If we combine using containers with implicits, we get “ad-hoc” polymorphism: the ability to write generic functions over containers.

  trait Container[M[_]] {
    def put[A](x: A): M[A]
    def get[A](m: M[A]): A
  }

  implicit val listContainer =
    new Container[List] {
      def put[A](x: A) = List(x)
      def get[A](m: List[A]) = m.head
    }

  implicit val optionContainer =
    new Container[Some] {
      def put[A](x: A) = Some(x)
      def get[A](m: Some[A]) = m.get
    }

  def tupleize[M[_]: Container, A, B](
    fst: M[A],
    snd: M[B]
  ): M[(A,B)] = {
     val c = implicitly[Container[M]]
     c.put(
       c.get(fst),
       c.get(snd)
     )
   }

  tupleize(Some(1), Some(2))
//  res33: Some[(Int, Int)] = Some((1,2))

  tupleize(List(1), List(2))
//  res34: List[(Int, Int)] = List((1,2))
}
