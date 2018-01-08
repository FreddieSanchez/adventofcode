package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day5._

class Day5 extends FunSuite {

  test("part1 - valid jump") {
    assert(jump(List(0,3,0,1,-3), 0) == (List(1,3,0,1,-3),0))
  }

  test("part1 - valid jump 1") {
    assert(jump(List(1,3,0,1,-3), 0) == (List(2,3,0,1,-3),1))
  }

  test("part1 - valid jump 2") {
    assert(jump(List(2,3,0,1,-3), 1) == (List(2,4,0,1,-3),3))
  }

  test("solve part1 test") {

    val jumps =  solvePart1(List(0,3,0,1,-3),0,0)
    assert(jumps == 5)
  }
}


