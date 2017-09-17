package com.example
import scala212.bin_incompat

object LibB {
  def use_AddFunctionDefaultParameter = {
    bin_incompat.AddFunctionDefaultParameter.func("asdf")
  }

  def use_ModifyCaseClassFields = {
    import scala212.all_incompat.ModifyCaseClassFields.SomeCaseClass
    val obj = SomeCaseClass("first", "second")
    obj match {
      case SomeCaseClass(first, second) => // do nothing
    }
  }

  def use_InferReturnTypes = {
    import scala212.bin_incompat.InferReturnTypes._
    returnTypeIsInferred
  }

  def use_PackagePrivate: String = {
    import scala212.src_incompat.PackagePrivate

    new PackagePrivate().method("a string", 5)
  }

  import scala211.bin_incompat.AddTraitDefaultMethod.SomeTrait
  def use_AddTraitDefaultMethod: SomeTrait = {
    import scala211.bin_incompat.AddTraitDefaultMethod._
    new SomeTrait {
      override def foo = "overridden foo"
    }
  }
}
