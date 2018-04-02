package kata.brackets

import java.util.stream

import scala.annotation.tailrec
import scala.collection.JavaConverters._

class TailRecursiveChecker extends GrammarChecker {
  override def isBalanced(tokenStream: stream.Stream[Token]): Boolean = {
    val tokens = tokenStream.iterator().asScala
    isBalanced(0, tokens)
  }

  @tailrec
  private def isBalanced(depth: Int, tokens: Iterator[Token]): Boolean = {
    if (!tokens.hasNext || depth < 0) return depth == 0

    val newDepth = tokens.next() match {
      case Token.OPEN_BRACKET => depth+1
      case Token.CLOSE_BRACKET => depth-1
    }

    isBalanced(newDepth, tokens)
  }
}
