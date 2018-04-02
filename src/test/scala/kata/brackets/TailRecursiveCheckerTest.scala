package kata.brackets

class TailRecursiveCheckerTest extends ScalaBracketsTest {
  override protected def checker: GrammarChecker = new TailRecursiveChecker()
}
