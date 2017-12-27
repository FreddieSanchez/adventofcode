package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day1._

class Day1 extends FunSuite {

  test("Single Element List") {
    assert(compress(List(1)) === List(List(1)))
  }
  test("Multiple Element List") {
    val list = List(0,1,2,3,4,5,6,7,8,9)
    assert(compress(list) === list.map(x=>List(x)))
  }
  test("Repeatative list") {
    val list = (0 to 4).map(x => List(x,x,x,x)).toList
    val flat = (0 to 4).flatMap(x => List(x,x,x,x)).toList
    assert(compress(flat) === list)
  }

  test("Encode 1122") {
    assert(encode(List(1,1,2,2)) === List((1,2),(2,2)))
  }
  test("Encode 1111") {
    assert(encode(List(1,1,1,1)) === List((1,4)))
  }
  test("Encode 1234") {
    assert(encode(List(1,2,3,4)) === List((1,1),(2,1),(3,1),(4,1)))
  }

  test("captcha 1122") {
    assert(part1Captcha(List(1,1,2,2)) === 3)
  }

  test("captcha 1111") {
    assert(part1Captcha(List(1,1,1,1)) === 4)
  }

  test("captcha 1234") {
    assert(part1Captcha(List(1,2,3,4)) === 0)
  }
  test("captcha 91212129") {
    assert(part1Captcha(List(9,1,2,1,2,1,2,9)) ===  9)
  }
  
  test("splitAndZip part 2 1212") {
    assert(splitAndZip(List(1,2,1,2)) === List(1,1,2,2))
  }
  test("splitAndZip part 2 1221") {
    assert(splitAndZip(List(1,2,2,1)) === List(1,2,2,1))
  }
  test("captcha part 2 1212") {
    assert(part2Captcha(List(1,2,1,2)) === 6)
  }
  test("captcha part 2 123425") {
    assert(part2Captcha(List(1,2,3,4, 2, 5)) === 4)
  }

  test("captcha part 2 123123") {
    assert(part2Captcha(List(1,2,3,1, 2, 3)) === 12)
  }

  test("captcha part 2 12131415") {
    assert(part2Captcha(List(1,2,1,3, 2, 4,1, 5)) === 4)
  }

}

