package scala.types

object Kinds {

  /*
  One of the more powerful features Scala has is the ability to generically abstract across things that take type parameters.
  This feature is known as Higher Kinded Types (HKT).

  This feature allows us to write a library that works with a much wider array of classes,
    whereas without the feature you are condemned to bespoke and error ridden code duplication for each class that may want the functionality.

    Kinds
  Now that we have a type constructor we can think of several different kinds of them, classified by how many type parameters they take.
  The simplest – like List[_] – that take a single param have the kind:

  (* -> *)

  This says: given one type, produce another. For instance, given String produce the type List[String].

  Something that takes two parameters, say Map[_, _], or Function1[_, _] has the kind:

  (* -> * -> *)

  This says: given one type, then another, produce the final type.
  For instance given the key type Int and the value type String produce the type Map[Int, String].

  Furthermore, you can have kinds that are themselves parameterized by higher kinded types.
  So, something could not only take a type, but take something that itself takes type parameters.
  An example would be the covariant functor: Functor[F[_]], it has the kind:

  ((* -> *) -> *)

  This says: given a simple higher kinded type, produce the final type.
  For instance given a type constructor like List produce the final type Functor[List].

   */

}
