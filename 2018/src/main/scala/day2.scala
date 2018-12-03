package adventofcode2018

import scala.io.Source 

object Day2 {

  def groupedByChar(cs:List[String]) : List[Map[Char, Int]] =
    cs.map(_.groupBy(identity).map { case (k, v) => k -> v.length })

  def part1(cs:List[String]):Int = {
    checksum(groupedByChar(cs))
  }

  def checksum(cs:List[Map[Char, Int]]):Int = countChars(cs,2) * countChars(cs,3)

  def countChars(cs: List[Map[Char, Int]], count:Int): Int =
    cs.foldLeft(0)( 
      (sum, map) => 
        if (map.exists { case (key:Char, value:Int) => value == count }) sum + 1
        else sum
        )

  def part2(cs:List[String]) = {
    cs.map(x => x -> 
      cs.map(y => y -> y.zipWithIndex.toVector.diff(x.zipWithIndex.toVector).size)
        .filter(_._2 == 1))
      .filter(!_._2.isEmpty)
      .map(x => (x._1, x._2.head._1))
      .map(x => (x._1.toVector.intersect(x._2.toVector)))
      .map(_.mkString)
      .head

  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/2.input").getLines.toList
    println("Part One : " + part1(lines))
    println("Part Two : " + part2(lines))
  }
  

}

