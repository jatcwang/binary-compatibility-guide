package scala212.bin_incompat

object InferReturnTypes {

  sealed trait Foo
  case class Bar() extends Foo
  case class Meh() extends Foo
  case class Tat() extends Foo

  def returnTypeIsInferred: Foo = {
    if (true) Bar() else Meh()
  }

}
