package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day7._

class Day7 extends FunSuite {

    val line = """pbga (66)
    |xhth (57)
    |ebii (61)
    |havc (66)
    |ktlj (57)
    |fwft (72) -> ktlj, cntj, xhth
    |qoyq (66)
    |padx (45) -> pbga, havc, qoyq
    |tknk (41) -> ugml, padx, fwft
    |jptl (61)
    |ugml (68) -> gyxo, ebii, jptl
    |gyxo (61)
    |cntj (57)
    """.stripMargin

   val lines = line.split("\n").toList
   lazy val parsed = parse(lines)
   
  test("root 1") {
    val root = bottomProgram(parsed)

    assert(root match {
      case Some(x) => x.name == "tknk"
      case _ => false})
  }

  test("isBalanced ugml") {
    val balance = isBalanced(parsed, Program("ugml", 68, Set("gyxo", "ebii", "jptl")))
    
    assert(balance)
  }

  test("isBalanced tknk") {
    val balance = isBalanced(parsed, Program("tknk", 41, Set("ugml","padx", "fwft")))
    assert(!balance)
  }
}

