package specs

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import util.Logging

abstract class UnitSpec extends FlatSpec
  with Matchers with OptionValues
  with Inside with Inspectors
  with BeforeAndAfter with BeforeAndAfterEach with BeforeAndAfterAll
  with GeneratorDrivenPropertyChecks
  with Logging
