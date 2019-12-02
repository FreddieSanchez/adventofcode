package adventofcode2019

import org.scalatest._

class Day2Test extends FunSuite
                      with BeforeAndAfterAll
                      with Matchers {

  test("Add OpCode") {
    val ints = "1,9,10,3,2,3,11,0,99,30,40,50".split(",").map(_.toInt).toList
    OpCode.Add.calculate(ints, 0) should be (70)
  }


  test("Multiply OpCode") {
    val ints = "1,9,10,70,2,3,11,0,99,30,40,50".split(",").map(_.toInt).toList
    OpCode.Multiply.calculate(ints, 4) should be (3500)
  }


}

