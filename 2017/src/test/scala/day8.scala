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
    |cntj (57)""".stripMargin

   val lines = line.split("\n").toList
   Day7.programs = parse(lines)
   
  test("root 1") {
    val root = bottomProgram

    assert(root match {
      case Some(x) => x.name == "tknk"
      case None => false})
  }

  test("calc weights") {
    val p = programs.find(p => p.name == "ugml").get
    println(p)
    assert(calcWeights(p) == 251)
  }
  test("calc weights 1") {
    val p = programs.find(p => p.name == "padx").get
    assert(calcWeights(p) == 243)
  }

  test("calc weights 2") {
    val p = programs.find(p => p.name == "fwft").get
    assert(calcWeights(p) == 243)
  }
  test("calc weights 3") {
    val p = programs.find(p => p.name == "tknk").get
    assert(calcWeights(p) == 41 + (251 + 243 + 243))
  }

  test("weights 1") {
    val p = programs.find(p => p.name == "tknk").get
    val p1 = programs.find(p => p.name == "ugml").get
    assert(unBalancedChild(p).get == p1)
  }
  test("part 2") {
    val p = programs.find(p => p.name == "tknk").get
    println(part2(p,None))
  }
}

