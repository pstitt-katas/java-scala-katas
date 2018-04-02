package kata.brackets

import kata.brackets.functional.RecursiveCheckerWithObjects
import org.scalatest.Ignore

@Ignore
class RecursiveCheckerWithObjectsTest extends ScalaBracketsTest {
  override protected def checker: GrammarChecker = new RecursiveCheckerWithObjects()
}
