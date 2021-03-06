package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day8._

class Day8 extends FunSuite {


  val lines = """b inc 5 if a > 1
                |a inc 1 if b < 5
                |c dec -10 if a >= 1
                |c inc -20 if c == 10""".stripMargin.split("\n").toList

  test("test part1") {
    
    val parsed = parse(lines)
    val max = Run(parsed).values.max

    assert(max == 1)
  }
  test("test part2") {
    
    val parsed = parse(lines)
    val max = Run2(parsed).map(reg => if (reg.values.isEmpty) 0 else reg.values.max).max

    assert(max == 10)
  }
}

