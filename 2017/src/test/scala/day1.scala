package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day1._

class Day1 extends FunSuite {

  test("captcha 1122") {
    assert(part1(List(1,1,2,2)) === 3)
  }

  test("captcha 1111") {
    assert(part1(List(1,1,1,1)) === 4)
  }

  test("captcha 1234") {
    assert(part1(List(1,2,3,4)) === 0)
  }
  test("captcha 91212129") {
    assert(part1(List(9,1,2,1,2,1,2,9)) ===  9)
  }
  test("captcha part 2 1212") {
    assert(part2(List(1,2,1,2)) === 6)
  }
  test("captcha part 2 123425") {
    assert(part2(List(1,2,3,4, 2, 5)) === 4)
  }

  test("captcha part 2 123123") {
    assert(part2(List(1,2,3,1, 2, 3)) === 12)
  }

  test("captcha part 2 12131415") {
    assert(part2(List(1,2,1,3, 1, 4,1, 5)) === 4)
  }

}

