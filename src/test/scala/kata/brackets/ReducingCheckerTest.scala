package kata.brackets

import kata.brackets.functional.ReducingChecker

class ReducingCheckerTest extends ScalaBracketsTest {
  override protected def checker: GrammarChecker = new ReducingChecker()
}
