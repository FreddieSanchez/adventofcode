package adventofcode2019

import org.scalatest._

class Day4Test extends FunSuite
                      with BeforeAndAfterAll
                      with Matchers {

    def isValid(num: Int, part1: Boolean, part2: Boolean) = {
      val range = num-1 to num + 1
      println(s"$num Part 1")
      Password(num, range).isValid1 should be (part1)
      println(s"$num Part 2")
      Password(num, range).isValid2 should be (part2)
    }


    test("should pass all examples") {

     isValid(112233, true, true)
     isValid(123444, true, false)
     isValid(111111, true, false)
     isValid(223450, false, false)
     isValid(123789, false, false)
    }
}
