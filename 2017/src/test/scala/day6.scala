package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day6._

class Day6 extends FunSuite {

  test("redistribute 1") {

    val vec =  redistribute(Vector(0,2,3,4),3)
    assert(vec == Vector(1, 3, 4, 1))
  }
  test("solve test 1") {

    val times = solvePart1(Vector(0,2,7,0))
    assert(5 == times)
  }
  test("solve test 2") {

    val times = solvePart2(Vector(0,2,7,0))
    assert(4 == times)
  }
}


