package adventofcode2017
import scala.io.Source 
object Day1 {

  def part1(nums:List[Int]):Integer = {
    val split = nums.splitAt(1)
    val rotated = split._2:::split._1
    nums.zip(rotated).filter(x => x._1 == x._2).map(_._1).sum
  }


  def part2(nums:List[Int]):Integer = {
    val split = nums.splitAt(nums.length / 2)
    val rotated = split._2:::split._1
    nums.zip(rotated).filter(x => x._1 == x._2).map(_._1).sum
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/1.input").getLines.toList
    val nums = lines.flatMap(x => x).map(_.asDigit)
    println("Part One take 2: " + part1(nums))
    println("Part Two take 2: " + part2(nums))
  }
  
}
