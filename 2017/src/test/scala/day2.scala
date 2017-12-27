package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day2._

class Day2 extends FunSuite {

  test("part1 checksum") {
    val l = "5\t1\t9\t5\n7\t5\t3\n2\t4\t6\t8"
    val lines = l.split("\n").toList
    val parsed = parseInput(lines)
    assert(part1(parsed) === 18)
  }

  test("part2 checksum") {
    val l = "5\t9\t2\t8\n9\t4\t7\t3\n3\t8\t6\t5"
    val lines = l.split("\n").toList 
    val parsed = parseInput(lines)
    assert(part2(parsed) === 9 )
  }

}


