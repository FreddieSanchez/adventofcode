package adventofcode2017
import scala.io.Source 
object Day3 {


  def distance(x:Int, y:Int): Int = Math.abs(x) + Math.abs(y)
  
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

  def part2(num:Int): Int = {
    case class Point(x:Int, y:Int)

    object Direction extends Enumeration {
      type Direction = Value
      val Up, Down, Left, Right = Value
    }
    def sumNeighbors(p:Point, grid:Map[Point,Int]):Int = {
      val neighbors = List(
         Point(p.x + 1, p.y), // right
         Point(p.x - 1, p.y), // left
         Point(p.x, p.y + 1), // up 
         Point(p.x, p.y - 1), // down 
         Point(p.x + 1, p.y + 1), // upper-left
         Point(p.x - 1, p.y + 1), // upper-right
         Point(p.x + 1, p.y - 1), // lower-left
         Point(p.x - 1, p.y - 1) // lower-right
       )
      val gridNeighbors = grid.filterKeys( key => neighbors.contains(key))
      gridNeighbors.values.foldLeft(0)( (accum, value) => accum + value)
      
    }
    import Direction._
    def move(p:Point, direction:Direction):Point = {
      direction match {
        case Up => Point(p.x, p.y + 1)
        case Down => Point(p.x, p.y -1)
        case Left => Point(p.x - 1, p.y)
        case Right => Point(p.x + 1, p.y)
      }
    }


    /* 
     * Finds a new direction given the current direction, and if it can turn in the new driection
     */
    def turnOrStraight(p:Point, direction:Direction, grid:Map[Point,Int]):Direction = {

      def canTurn(newDirection:Direction): Boolean = !grid.contains(move(p,newDirection))

      direction match {
        case Up =>   if (canTurn(Left)) Left else Up
        case Down => if (canTurn(Right)) Right else Down
        case Left => if (canTurn(Down)) Down else Left 
        case Right =>if (canTurn(Up)) Up else Right
      }

    }
    
    @annotation.tailrec
    def findNum(number:Int, point:Point, direction:Direction, grid:Map[Point,Int]):Int = {

      val newPoint = move(point, direction) 
      val value = sumNeighbors(newPoint, grid)
      val newGrid = grid + (newPoint -> value)


      val newDirection = turnOrStraight(newPoint, direction, grid)
      if (number > value) findNum(number, newPoint, newDirection, newGrid)
      else value
    }

    val Grid = Map( Point(0,0) -> 1, Point(1,0) -> 1)
    findNum(num, Point(1,0), Up,  Grid)
    
  }

  def parseInput(lines: List[String]): Int =  lines.map(_.toInt).head

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/3.input").getLines.toList
    val parsed = parseInput(lines)
    println("Part One: " + part1(parsed))
    println("Part Two: " + part2(parsed)) }
  
}
