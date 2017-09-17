package scala212.bin_incompat

object InferReturnTypes {

  sealed trait Foo
  case class Bar() extends Foo
  case class Meh() extends Foo
  case class Tat() extends Foo

  // Inferred type: Foo with Product with Serializable 
  def returnTypeIsInferred = {
    if (true) Bar() else Meh()
  }

}
