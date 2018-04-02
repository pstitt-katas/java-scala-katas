package kata.roman

import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, PropSpec}

class RomanTest extends PropSpec with PropertyChecks with Matchers {

  val converter: RomanNumeralConverter = new RomanNumeralConverter()

  // TODO add more examples - just started and have no proper solution yet...
  property("examples") {
    val testCases = Table("no more than 3 in a row",
      (1, "I")
    )

    forAll(testCases) { testCase =>
      val number = testCase._1
      val expectedNumeral = testCase._2

      converter.convert(number) shouldBe expectedNumeral
    }
  }
}

class RomanNumeralConverter {
  def convert(number: Int) : String = {
    "I"
  }
}
