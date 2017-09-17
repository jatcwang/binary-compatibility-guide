import com.example.LibA
import org.scalatest.FreeSpec

/**
  * These tests shows the result of the various tricks we can use to avoid breaking binary compatibility
  */
class CompatibilityShowcases extends FreeSpec {

  "Marking a method as package private does not break binary compatibility" in {
    LibA.use_PackagePrivate
  }
}
