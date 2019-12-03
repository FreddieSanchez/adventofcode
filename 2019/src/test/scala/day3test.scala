package adventofcode2019

import org.scalatest._

class Day3Test extends FunSuite
                      with BeforeAndAfterAll
                      with Matchers {

  test("R8,U5,L5,D3") {
     val wire = Wire.build("R8,U5,L5,D3".split(",").toList.map(Instruction(_)))
     wire should be(
       Wire(
         List(
           Line(Point(0,0),Point(8,0)),
           Line(Point(8,0), Point(8,5)),
           Line(Point(8,5), Point(3,5)),
           Line(Point(3,5), Point(3,2)))))
  }

  test("vertical & horizontal intersects") {

    val line1 = Line(Point(2,2), Point(2,5))
    val line2 = Line(Point(3,3), Point(1,3))

    line1.intersects(line2) should be(Some(Point(2, 3)))
  }

  test("horizontal & vertical intersects") {
    val line1 = Line(Point(2,2), Point(2,5))
    val line2 = Line(Point(3,3), Point(1,3))

    line2.intersects(line1) should be(Some(Point(2, 3)))
  }

  test("Parallel vertical lines don't intersect") {
    val line1 = Line(Point(2,2), Point(2,5))
    val line2 = Line(Point(1,1), Point(1,5))

    line1.intersects(line2) should be(None)
  }

  test("Parallel horizontal lines don't intersect") {
    val line1 = Line(Point(2,2), Point(3,2))
    val line2 = Line(Point(1,1), Point(4,1))

    line1.intersects(line2) should be(None)
  }

  test("criss crossing lines don't intersect") {
    val line1 = Line(Point(2,2), Point(3,2))
    val line2 = Line(Point(1,1), Point(1,4))

    line1.intersects(line2) should be(None)
  }
  test("vertical and vertical intersect") {
    val line1 = Line(Point(2,2), Point(2,5))
    val line2 = Line(Point(2,5), Point(2,6))

    line1.intersects(line2) should be(Some(Point(2,5)))
  }
  test("horizontal and horizontal intersect") {
    val line1 = Line(Point(2,2), Point(5,2))
    val line2 = Line(Point(5,2), Point(7,2))

    line1.intersects(line2) should be(Some(Point(5,2)))
  }

  test("distance to point not intersecting") {
    val line1 = Line(Point(2,2), Point(5,2))
    line1.length should be (3)
    line1.distanceToPoint(Point(3,4)) should be (line1.length)
  }

  test("distance to point intersecting") {
    val line1 = Line(Point(2,2), Point(7,2))
    line1.length should be (5)
    line1.distanceToPoint(Point(5,2)) should be (3)
  }

  test("distance to point 1 ") {
     val wire = Wire.build("R8,U5,L5,D3".split(",").toList.map(Instruction(_)))
     wire.distanceToPoint(Point(3,3)) should be (20)
  }
  test("distance to point 2") {
     val wire = Wire.build("U7,R6,D4,L4".split(",").toList.map(Instruction(_)))
     wire.distanceToPoint(Point(3,3)) should be (20)
  }

  test("intersecting wire length") {
     val wire1 = Wire.build("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(",").toList.map(Instruction(_)))
     val wire2 = Wire.build("U62,R66,U55,R34,D71,R55,D58,R83".split(",").toList.map(Instruction(_)))
     val distance = wire1.intersects(wire2).filterNot(p => p == Point(0,0)).map( pt =>
     wire1.distanceToPoint(pt) + wire2.distanceToPoint(pt)).min
     distance should be(610)
  }

  test("intersecting wire length 2") {
     val wire1 = Wire.build("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(",").toList.map(Instruction(_)))
     val wire2 = Wire.build("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(",").toList.map(Instruction(_)))
     val distance = wire1.intersects(wire2).filterNot(p => p == Point(0,0)).map( pt =>
     wire1.distanceToPoint(pt) + wire2.distanceToPoint(pt)).min
     distance should be(410)
  }
}
