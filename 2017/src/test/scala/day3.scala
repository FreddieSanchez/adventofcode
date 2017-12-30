package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day3._

class Day3 extends FunSuite {

  test("part1 distance") {
    assert(part1(1) === 0)
  }

  test("part1 12") {
    assert(part1(12) === 3 )
  }
  test("part1 23") {
    assert(part1(23) === 2 )
  }

  test("part1 1024") {
    assert(part1(1024) === 31 )
  }
}


