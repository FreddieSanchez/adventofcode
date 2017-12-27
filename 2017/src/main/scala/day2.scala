package adventofcode2017
import scala.io.Source 
object Day2 {

  def part1(nums:List[List[Int]]):Integer = {
    nums.map(x => x.max - x.min)
        .sum
  }

  def findTwoDivideEvently(nums: List[Int]): List[(Int,Int)] = {
    nums.flatMap(x => nums.filter(y => x % y == 0 && x != y).map(y => (x,y)))
	}

  def part2(nums:List[List[Int]]): Integer = {
    nums.flatMap(findTwoDivideEvently)
        .map(x => x._1 / x._2)
        .sum
  }

  def parseInput(lines: List[String]): List[List[Int]] = {
    
    lines.map(s => s.split("\t").toList)
         .map(x => x.map(_.toInt)) 
  }
  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/2.input").getLines.toList
    val parsed = parseInput(lines)
    println("Part One: " + part1(parsed))
    println("Part Two: " + part2(parsed))
  }
  
}
