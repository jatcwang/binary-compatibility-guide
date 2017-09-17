package scala211.bin_incompat

object AddTraitDefaultMethod {
  trait SomeTrait {
    def foo: String

    def defaultMethod: String = "default"
  }

  class ClassExtendingTrait extends SomeTrait {
    override def foo: String = "foo"
  }
}
