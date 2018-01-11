package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day5._

class Day5 extends FunSuite {

  test("solve part1 test") {

    val jumps =  solve(Vector(0,3,0,1,-3),0,0, {x => x + 1})
    assert(jumps == 5)
  }
  test("solve part2 test") {

    val jumps =  solve(Vector(0,3,0,1,-3),0,0, {x => if (x >= 3) x - 1 else x + 1})
    assert(jumps == 10)
  }
}


