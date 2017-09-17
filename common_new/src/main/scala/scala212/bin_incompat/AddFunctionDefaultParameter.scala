package scala212.bin_incompat

object AddFunctionDefaultParameter {

  def func(p1: String, aDefaultP: String = "default") = {
    p1
  }

}
