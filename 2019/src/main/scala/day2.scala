package adventofcode2019

import scala.io.Source
import enumeratum.values._
import scala.collection.mutable.{Map => MMap}

sealed abstract class OpCode(val value: Int, val name: String) extends IntEnumEntry

case object OpCode extends IntEnum[OpCode] {
  case object Add extends OpCode(value = 1, name = "Add") {
    def calculate(ints: List[Int], index: Int): Int =
      ints(ints(index + 1)) + ints(ints(index + 2))

  }
  case object Multiply extends OpCode(value = 2, name = "Multiply") {
    def calculate(ints: List[Int], index: Int): Int =
      ints(ints(index + 1)) * ints(ints(index + 2))
  }
  case object Halt extends OpCode(value = 99, name = "Halt")
  val values = findValues
}

case class Next(code: IntCode, index: Int)

case class IntCode(ints: List[Int]) {
  import OpCode._
  def process(index: Int): Next = {
    OpCode.withValue(ints(index)) match {
      case Add => Next(IntCode(ints.updated(ints(index + 3), Add.calculate(ints, index))), index + 4)
      case Multiply => Next(IntCode(ints.updated(ints(index + 3), Multiply.calculate(ints, index))), index + 4)
      case Halt =>  Next(this, index)
      case _ => ???
    }
  }

  def value(index: Int): Int = ints(index)
}

object IntCodeRunnder {
  private def run(prev: Next): IntCode = {
    val current = prev.code.process(prev.index)
    if (current.index == prev.index)
      current.code
    else
      run(current)
  }
  def run(intCode: IntCode): IntCode =
    run(Next(intCode, 0))

}

object Day2 {

  def part1(ints: List[Int]): Int = {
    val code = IntCode(ints.updated(1, 12).updated(2, 2))
    IntCodeRunnder.run(code).value(0)
  }


  def part2(ints: List[Int]): Option[Int] = {
    def _part2(noun: Int, verb: Int): Option[(Int, Int)] = {
        val code = IntCode(ints.updated(1, noun).updated(2, verb))
        if (IntCodeRunnder.run(code).value(0) == 19690720)
         Some(noun, verb)
        else
          None
    }

    val results = for {
      noun <- 0 to 99
      verb <- 0 to 99
    } yield _part2(noun, verb)

    results.filter(_.nonEmpty).headOption.flatten.map { case (x, y) => 100 * x + y }

  }
  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/2.input").getLines.toList.head.split(",").map(_.toInt).toList
    println("Part One : " + part1(lines))
    println("Part Two : " + part2(lines))
  }

}
