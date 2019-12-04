package adventofcode2019

import scala.io.Source

case class Password(number: Int, range: Range) {
  def twoAdjacent(n: String): Boolean =
    n.sliding(2).exists(p => p(0) == p(1))

  def neverDecrease(n: String): Boolean =
    n == n.sorted

  def adjacentInPairs(n: String): Boolean =
    n.groupBy(identity).values.exists(_.length() == 2)

  private  val num: String = number.toString

  def isValid1: Boolean = {
    num.length == 6 &&
    range.contains(number) &&
    twoAdjacent(num) &&
    neverDecrease(num)
  }

  def isValid2: Boolean = {
    num.length == 6 &&
    range.contains(number) &&
    neverDecrease(num) &&
    adjacentInPairs(num)
  }

}

object Day4 {

  def part1(range: Range): Int = {
    range.filter(p => Password(p, range).isValid1).length
  }

  def part2(range: Range): Int = {
    range.filter(p => Password(p, range).isValid2).length
  }

  def main(args: Array[String]): Unit = {
    val range = Source.fromFile("./src/main/scala/4.input").getLines.toList.head.split("-").take(2).map(_.toInt)
    val r = range(0) to range(1)
    println("*******")
    println("Part One : " + part1(r))
    println("Part Two : " + part2(r))
    println("*******")
  }
}
