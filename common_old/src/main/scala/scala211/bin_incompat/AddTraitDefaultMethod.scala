package scala211.bin_incompat

object AddTraitDefaultMethod {
  trait SomeTrait {
    def foo: String
  }

  class ClassExtendingTrait extends SomeTrait {
    override def foo: String = "foo"
  }
}
