package scala.types

object FBoundedPolymorphism {

//  Often it’s necessary to access a concrete subclass in a (generic) trait.
  // For example, imagine you had some trait that is generic, but can be compared to a particular subclass of that trait.

  trait Container extends Ordered[Container]

//  However, this now necessitates the subclass to implement the compare method
  def compare(that: Container): Int = ???

//  And so we cannot access the concrete subtype, e.g.:
  class MyContainer extends Container {
    def compare(that: MyContainer): Int = ???
  }
//  fails to compile, since we are specifying Ordered for Container, not the particular subtype.



//  Then an alternative solution would be parameterize Container so that we can access the subtype in the subclass.
  trait Container[A] extends Ordered[A]

//  The subclass could now do the following:
  class MyContainer extends Container[MyContainer] {
    def compare(that: MyContainer): Int = ???
  }

//  But the problem is that the type A is not bounded by anything, and you could potentially do something like this:
  class MyContainer extends Container[String] {
    def compare(that: String): Int = ???
  }



//  To reconcile this, we instead use F-bounded polymorphism.
  trait Container[A <: Container[A]] extends Ordered[A]
//  Strange type! But note now how Ordered is parameterized on A, which itself is Container[A]

//  So, now
  class MyContainer extends Container[MyContainer] {
    def compare(that: MyContainer) = 0
  }
//  They are now ordered:

  List(new MyContainer, new MyContainer, new MyContainer)
//  res3: List[MyContainer] = List(MyContainer@30f02a6d, MyContainer@67717334, MyContainer@49428ffa)

  List(new MyContainer, new MyContainer, new MyContainer).min
//  res4: MyContainer = MyContainer@33dfeb30

//  Given that they are all subtypes of Container[_], we can define another subclass & create a mixed list of Container[_]:
  class YourContainer extends Container[YourContainer] {
    def compare(that: YourContainer) = 0
  }

  List(new MyContainer, new MyContainer, new MyContainer, new YourContainer)
//  res2: List[Container[_ >: YourContainer with MyContainer <: Container[_ >: YourContainer with MyContainer <: ScalaObject]]]
//  List(MyContainer@3be5d207, MyContainer@6d3fe849, MyContainer@7eab48a7, YourContainer@1f2f0ce9)
//  Note how the resulting type is now lower-bound by YourContainer with MyContainer.
  // This is the work of the type inferencer.
  // Interestingly- this type doesn’t even need to make sense, it only provides a logical greatest lower bound for the unified type of the list.
  // hat happens if we try to use Ordered now?

  (new MyContainer, new MyContainer, new MyContainer, new YourContainer).min
//  error: could not find implicit value for parameter cmp:
//    Ordering[Container[_ >: YourContainer with MyContainer <: Container[_ >: YourContainer with MyContainer <: ScalaObject]]]
//    No Ordered[] exists for the unified type. Too bad.
}
