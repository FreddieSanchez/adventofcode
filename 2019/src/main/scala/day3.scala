package adventofcode2019

import scala.io.Source
import enumeratum.values._
import scala.collection.mutable.{Map => MMap}


case class Point(x: Int, y: Int) {
  def distance(that: Point): Int = {
    Math.abs(this.x - that.x) + Math.abs(this.y - that.y)
  }
}

object implicits {
  implicit class IntOps(x: Int) {
    def between(x1: Int, x2: Int): Option[Int] =
      if (Math.min(x1, x2) to Math.max(x1, x2) contains x)
        Some(x)
      else
        None
  }
}
case class Line(start: Point, end: Point) {

  import implicits._
  val horizontal: Boolean = start.y == end.y
  val vertical : Boolean = start.x == end.x
  val length: Int = start.distance(end)
  def intersects(line: Line): Option[Point] = {

    val (containsX: Option[Int], containsY: Option[Int]) =
      if (vertical && line.horizontal) {
      val x = start.x.between(line.start.x, line.end.x)
      val y = line.start.y.between(start.y, end.y)
      (x, y)
    } else if (vertical && line.vertical) {
      val x = line.start.x.between(start.x, end.x)
      val y = line.start.y.between(start.y, end.y)
      (x, y)
    } else if(horizontal && line.vertical) {
      val x = line.start.x.between(start.x, end.x)
      val y = start.y.between(line.start.y, line.end.y)
      (x, y)
    } else  {
      val x = line.start.x.between(start.x, end.x)
      val y = line.start.y.between(start.y, end.y)
      (x, y)
    }

    for {
      x <- containsX
      y <- containsY
    } yield Point(x,y)
  }
  def distanceToPoint(pt: Point): Int =
    intersects(Line(pt, pt)).map(start.distance(_)).getOrElse(length)
}

case class Wire(segments: List[Line]) {
  def intersects(wire: Wire): List[Point] = {
    (for {
      that <- wire.segments
      line <- segments
    } yield {
      that.intersects(line)
    }).filter(_.nonEmpty).flatten
  }

  def distanceToPoint(point: Point): Int =
    segments.foldLeft((0, false)) { case((acc, encounteredPoint), line) =>
      if (!encounteredPoint) {
        val encountered = line.distanceToPoint(point) != line.length
        val sum = acc + line.distanceToPoint(point)
        (sum, encountered)
      } else
        (acc, encounteredPoint)
    }._1
}

sealed trait Direction
case object Up extends Direction
case object Down extends Direction
case object Right extends Direction
case object Left extends Direction


case class Instruction(direction: Direction, distance: Int) {
  def endPoint(startPoint: Point): Point =
    direction match {
      case Up => startPoint.copy(y = startPoint.y + distance)
      case Down => startPoint.copy(y = startPoint.y - distance)
      case Right => startPoint.copy(x = startPoint.x + distance)
      case Left => startPoint.copy(x = startPoint.x - distance)
      case _ => ???
    }
}

object Instruction {
  def apply(string: String): Instruction =  {
    val distance = string.tail.toInt
    string.head match {
      case 'U' => Instruction(Up, distance)
      case 'D' => Instruction(Down, distance)
      case 'L' => Instruction(Left, distance)
      case 'R' => Instruction(Right, distance)
      case _ => ???
    }
  }
}
object Wire {
  val empty: Wire = Wire(Nil)
  def build(instructions: List[Instruction]): Wire = {
    val zero = Point(x = 0, y = 0)
    val (endpt, wire) = instructions.foldLeft((zero, empty)){ case((startPt, wire), instruction) =>
        val endPt = instruction.endPoint(startPt)
        val lines = wire.segments ::: List(Line(startPt, endPt))
        val newWire= wire.copy(segments = lines)
        (endPt, newWire)
    }
    wire
  }

}

object Day3 {

  def part1(wires: List[Wire]): Int = {
    val intersects = wires(0).intersects(wires(1)).filterNot(p => p == Point(0,0)).minBy( pt => pt.distance(Point(0,0)))
    intersects.distance(Point(0,0))
  }


  def part2(wires: List[Wire]): Int = {
    val wire1 = wires(0)
    val wire2 = wires(1)
    val distance = wire1.intersects(wire2).filterNot(p => p == Point(0,0)).map( pt =>
        wire1.distanceToPoint(pt) + wire2.distanceToPoint(pt)
        ).min
    distance

  }


  def main(args: Array[String]): Unit = {
    val wires = Source.fromFile("./src/main/scala/3.input").getLines.map(_.split(",").toList.map(Instruction(_))).toList.map(Wire.build(_))
    println("Part One : " + part1(wires))
    println("Part Two : " + part2(wires))
  }
}
