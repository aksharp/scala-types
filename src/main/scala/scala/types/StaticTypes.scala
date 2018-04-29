package scala.types

object StaticTypes {

/*

What are static types? Why are they useful?

According to Pierce:
“A type system is a syntactic method for automatically checking the absence
of certain erroneous behaviors by classifying program phrases
according to the kinds of values they compute.”

Functions in Mathematics
  Function “f” maps values
    from the set of real numbers
    to values of the set of natural numbers.

  f: R -> N

Type systems give us powerful ways to express these sets.

Compiler can now statically (at compile time) verify that the program is sound

That is, compilation will fail if values (at runtime) will not comply to the constraints imposed by the program.

With increasing expressiveness in type systems,
we can produce more reliable code because it allows us to prove invariants about our program before it even runs.

Note that all type information is removed at compile time. It is no longer needed. This is called erasure.

*/

  val question = "What are static types? Why are they useful?"

  val answer: Int = "Because this will be caught at compile time!"


}

