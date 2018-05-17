package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day9._

class Day9 extends FunSuite {


  test("test part1 {}") {
    
    val total = part1("{}".toList)
    assert(total == 1)
  }
  test("test part1 {{}}") {
    
    val total = part1("{{}}".toList)
    assert(total == 3)
  }
  test("test part1 {{{}}}") {
    
    val total = part1("{{{}}}".toList)
    assert(total == 6)
  }
  test("test part1 {{},{}}") {
    
    val total = part1("{{},{}}".toList)
    assert(total == 5)
  }

  test("test part1 {{{},{},{{}}}}") {
    
    val total = part1("{{{},{},{{}}}}".toList)
    assert(total == 16)
  }

  test("test part1 {<a>,<a>,<a>,<a>}") {
    val total = part1("{<a>,<a>,<a>,<a>}".toList)
    assert(total == 1)
  }
  test("test part1 {{<ab>},{<ab>},{<ab>},{<ab>}}") {
    val total = part1("{{<ab>},{<ab>},{<ab>},{<ab>}}".toList)
    assert(total == 9)
  }
  test("test part1 {{<!!>},{<!!>},{<!!>},{<!!>}},") {
    val total = part1("{{<!!>},{<!!>},{<!!>},{<!!>}},".toList)
    assert(total == 9)
  }
  test("test part1 {{<a!>},{<a!>},{<a!>},{<ab>}},") {
    val total = part1("{{<a!>},{<a!>},{<a!>},{<ab>}},".toList)
    assert(total == 3)
  }

  test("test part2 <>") {
    val total = part2("<>".toList).length
    assert(total == 0)
  }
  test("test part2 <random characters>") {
    val total = part2("<random characters>".toList).length
    assert(total == 17)
  }
  test("test part2 <<<<>") {
    val total = part2("<<<<>".toList).length
    assert(total == 3)
  }
  test("test part2 <{!>}>") {
    val total = part2("<{!>}>".toList).length
    assert(total == 2)
  }
  test("test part2 <!!>") {
    val total = part2("<!!>".toList).length
    assert(total == 0)
  }
  test("test part2 <{o\"i!a,<{i<a>") {
    val total = part2("<{o\"i!a,<{i<a>".toList).length
    assert(total == 10)
  }
}

