import com.example.{LibA, LibB}
import org.scalatest.FreeSpec

/**
  * These tests showcases some of the common causes of source/binary incompatibility
  */
class IncompatibilityShowcases extends FreeSpec {
  "Scala 2.12" - {
    "Binary Incompatible Changes" - {
      "Adding parameter with default values to methods" in {
        intercept[NoSuchMethodError] {
          LibA.use_AddFunctionDefaultParameter
        }
      }

      "Inferred return type changing when modifying method body" in {
        intercept[NoSuchMethodError] {
          LibA.use_InferReturnTypes
        }
      }
    }

    "Binary AND Source incompatible changes" - {
      "Modifying case class parameters" in {
        intercept[NoSuchMethodError] {
          LibA.use_ModifyCaseClassFields
        }
      }
    }
  }

  "Scala 2.11 (tests will fail for newer Scala versions because expected exceptions no longer occur)" - {
    "Binary Incompatible Changes" - {
      "Adding methods with default implementation to traits" in {
        intercept[AbstractMethodError] {
          LibA.use_AddTraitDefaultMethod.defaultMethod
        }
      }
    }
  }
}
