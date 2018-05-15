package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day9._

class Day9 extends FunSuite {


  test("test part1 {}") {
    
    val total = parse("{}".toList)
    assert(total == 1)
  }
  test("test part1 {{}}") {
    
    val total = parse("{{}}".toList)
    assert(total == 3)
  }
  test("test part1 {{{}}}") {
    
    val total = parse("{{{}}}".toList)
    assert(total == 6)
  }
  test("test part1 {{},{}}") {
    
    val total = parse("{{},{}}".toList)
    assert(total == 5)
  }

  test("test part1 {{{},{},{{}}}}") {
    
    val total = parse("{{{},{},{{}}}}".toList)
    assert(total == 16)
  }

  test("test part1 {<a>,<a>,<a>,<a>}") {
    val total = parse("{<a>,<a>,<a>,<a>}".toList)
    assert(total == 1)
  }
  test("test part1 {{<ab>},{<ab>},{<ab>},{<ab>}}") {
    val total = parse("{{<ab>},{<ab>},{<ab>},{<ab>}}".toList)
    assert(total == 9)
  }
  test("test part1 {{<!!>},{<!!>},{<!!>},{<!!>}},") {
    val total = parse("{{<!!>},{<!!>},{<!!>},{<!!>}},".toList)
    assert(total == 9)
  }
  test("test part1 {{<a!>},{<a!>},{<a!>},{<ab>}},") {
    val total = parse("{{<a!>},{<a!>},{<a!>},{<ab>}},".toList)
    assert(total == 3)
  }

}

