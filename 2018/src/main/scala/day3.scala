package adventofcode2018

import scala.io.Source 

object Day3 {

  case class Point(x:Int, y:Int)
  case class Claim(n:Int)

//#1 @ 287,428: 27x20
  def points(cs:List[String]):Map[Point, Set[Claim]] = 
    cs.foldLeft(Map():Map[Point, Set[Claim]])({ case (m, str) =>
      val pattern = raw"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)".r
      str match {
        case pattern(caseNum, x, y, w, h) => 
        {
          val width = w.toInt
          val height = h.toInt
          val xp = x.toInt
          val yp = y.toInt


          val points = 
            for( xP <- xp to (xp+width-1); yP <- yp to (yp+height-1)) 
              yield Map(Point(xP,yP) -> (Set(Claim(caseNum.toInt)) ++ m.getOrElse(Point(xP,yP), Set.empty:Set[Claim])))

              points.foldLeft(m)((mP, p2c) => mP ++ p2c)

        }
      }
    })

  def part1(cs:List[String]):Int = {
    points(cs).filter(x => x._2.size > 1).keySet.size
  }

  def part2(cs:List[String]):Claim = {
    val pointers = points(cs).filter(x => x._2.size == 1).foldLeft(Set.empty:Set[Claim]) ( (acc, kv) => 
        acc ++ kv._2 )
    val MorePointers  = points(cs).filter(x => x._2.size > 1).foldLeft(Set.empty:Set[Claim]) ( (acc, kv) => 
        acc ++ kv._2 )

    val singles = pointers diff MorePointers
    assert(singles.size == 1)
    singles.head
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/3.input").getLines.toList

    
    // val lines = """#1 @ 1,3: 4x4
    //            |#2 @ 3,1: 4x4
    //            |#3 @ 5,5: 2x2""".stripMargin.split("\n").toList
    //
    println("Part One : " + part1(lines))
    println("Part Two : " + part2(lines))
  }
  

}

