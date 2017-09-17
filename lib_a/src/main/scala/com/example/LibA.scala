package com.example
import scala212.bin_incompat

object LibA {
  def use_AddFunctionDefaultParameter = {
    bin_incompat.AddFunctionDefaultParameter.func("asdf")
  }

  def use_ModifyCaseClassFields = {
    import scala212.all_incompat.ModifyCaseClassFields.SomeCaseClass
    val obj = SomeCaseClass("first")
    obj match {
      case SomeCaseClass(first) => // do nothing - just using pattern matching
    }
  }

  def use_InferReturnTypes = {
    import scala212.bin_incompat.InferReturnTypes._
    returnTypeIsInferred
  }

  def use_PackagePrivate: String = {
    import scala212.src_incompat.PackagePrivate

    new PackagePrivate().method("a string")
  }

  import scala211.bin_incompat.AddTraitDefaultMethod.SomeTrait
  def use_AddTraitDefaultMethod: SomeTrait = {
    import scala211.bin_incompat.AddTraitDefaultMethod._
    new SomeTrait {
      override def foo = "overridden foo"
    }
  }

}
