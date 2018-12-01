package adventofcode2018

import scala.io.Source 

object Day1 {

  def part1(nums:List[Int]):Int = {
    nums.sum
  }


  def part2(nums:List[Int]): Int = {

    val adjustments:Stream[Int] = Stream.continually(nums.toStream).flatten

    val freqs: Stream[Int] = 
      adjustments.scanLeft(0) { 
        case (sum:Int, num:Int) => 
          sum + num
    }

    def firstDupe(nums: Stream[Int], seen:Set[Int]=Set.empty):Option[Int] = {
      nums match {
        case x#::xs if (seen.contains(x)) => Some(x)
        case x#::xs => firstDupe(xs, seen + x)
        case _ => None
      }
    }

    firstDupe(freqs).getOrElse(0)
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/1.input").getLines.toList.map(_.toInt)
    println("Part One : " + part1(lines))
    println("Part Two : " + part2(lines))
  }
  
}
