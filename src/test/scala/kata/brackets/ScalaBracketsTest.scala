package kata.brackets

import org.scalacheck.{Gen, Shrink}
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, PropSpec}
import util.Logging

abstract class ScalaBracketsTest extends PropSpec with PropertyChecks with Matchers with Logging {

  protected def checker: GrammarChecker

  /**
    * This is needed to prevent shrinking of generated Strings so we don't lose the
    * initial stackoverflow error for deeply nested bracket tests.
    */
  implicit val stringGenNoShrink = Shrink[String] {
    - => Stream.empty
  }

  property("balanced brackets are valid") {
    val examplesThatAreBalanced = Table("input",
      "", "[]", "[][]", "[[]]", "[[[[]][]][]]"
    )

    forAll(examplesThatAreBalanced) {
      isBalanced(_) shouldBe true
    }
  }

  property("unbalanced with unmatched open brackets are invalid") {
    val examplesThatHaveUnbalancedOpenBracket = Table("input",
      "[", "[][", "[[[]]", "[[[][]]"
    )

    forAll(examplesThatHaveUnbalancedOpenBracket) {
      isBalanced(_) shouldBe false
    }
  }

  property("unbalanced with unmatched close brackets are invalid") {
    val examplesThatHaveUnbalancedCloseBracket = Table("input",
      "]", "][", "[[[]][]]]"
    )

    forAll(examplesThatHaveUnbalancedCloseBracket) {
      isBalanced(_) shouldBe false
    }
  }

  property("invalid characters") {
    val examplesThatHaveInvalidCharacters = Table("input",
      "[a]", "[1]", "[ ]", "[\t]", "[\n]", "[]\r]", "$"
    )

    forAll(examplesThatHaveInvalidCharacters) {
      isBalanced(_) shouldBe false
    }
  }

  property("deeply balanced brackets are valid") {
    val MAX_DEPTH_OF_GENERATED_INPUTS = 20000

    val generateDeeplyNestedBalancedStrings: Gen[String] =
      for (depth <- Gen.choose(1, MAX_DEPTH_OF_GENERATED_INPUTS))
        yield generateSingleBalancedString(depth)

    forAll(generateDeeplyNestedBalancedStrings) { input =>
      isBalanced(input) shouldBe true
    }
  }

  private def isBalanced(input: String) = try {
    log.trace(s"checking input numberOfGroups=${input.length} : $input")
    checker.isBalanced(input)
  }
  catch {
    case x: ParserException =>
      false
  }

  private def generateSingleBalancedString(depth: Int) = {
    val builder = new StringBuilder(depth * 2)
    (1 until depth).foreach(_ => builder.append('['))
    (1 until depth).foreach(_ => builder.append(']'))
    builder.toString
  }
}
