package kata.brackets

import kata.brackets.oo.Parser

class ParserTest extends ScalaBracketsTest {
  override protected def checker: GrammarChecker = new Parser()
}
