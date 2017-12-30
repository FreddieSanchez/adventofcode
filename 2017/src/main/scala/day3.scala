package adventofcode2017
import scala.io.Source 
object Day3 {


  def distance(x:Int, y:Int): Int = Math.abs(x) + Math.abs(y)
  
  def part1BruteForce(num:Int):Integer = {
    ???
  }

  def part1(num:Int):Integer = {
    val distanceFromCenter = (Math.sqrt(num).ceil / 2 ).toInt

    // The odd square ex 1, 3, 5, 7, etc
    val oddSquare = distanceFromCenter * 2 + 1

    val square = Math.pow(oddSquare,2).toInt

    // The offset from the center
    val center = (oddSquare - 1) / 2              

    // The corners of the square
    val corners = for { 
                       x <- 0 to 3
                       y = square - (oddSquare - 1)*x 
                      } yield (y, y - distanceFromCenter)
   
    val (corner, mid) = corners.filter( x => num <= x._1).minBy(_._1)

    val y = if (distanceFromCenter != 0) (num - mid) % distanceFromCenter else 0
    
    distance(distanceFromCenter,y)
  }

  def part2(nums:List[List[Int]]): Int = {
    ???
  }

  def parseInput(lines: List[String]): Int =  lines.map(_.toInt).head

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/3.input").getLines.toList
    val parsed = parseInput(lines)
    println("Part One: " + part1(parsed))
    //println("Part Two: " + part2(parsed))
  }
  
}
