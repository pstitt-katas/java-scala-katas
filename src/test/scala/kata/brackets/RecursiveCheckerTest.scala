package kata.brackets

import kata.brackets.functional.RecursiveChecker
import org.scalatest.Ignore

@Ignore
class RecursiveCheckerTest extends ScalaBracketsTest {
  override protected def checker: GrammarChecker = new RecursiveChecker()
}
