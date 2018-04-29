package scala.types

object StructuralTypes {

//  Scala has support for structural types — type requirements are expressed by interface structure instead of a concrete type.

  def foo(x: { def get: Int }) = 123 + x.get
  //  foo: (x: AnyRef{def get: Int})Int

  foo(new { def get = 10 })
  //  res0: Int = 133


  // structural type constraint
  class HttpResponse[R <: { val status: Int }](response: R) {
    def status: Int = response.status
  }

  // types
  case class Ok(status: Int)
  case class BadRequest(status: Int, message: String)
  case class InvalidHttpStatus()

  // compiles
  val ok = new HttpResponse(Ok(200))
  val badRequest = new HttpResponse(BadRequest(400, "Bad"))

  // does not compile
  val invalid = new HttpResponse(InvalidHttpStatus)


  // This can be quite nice in many situations, but the implementation uses reflection, so be performance-aware!

}
