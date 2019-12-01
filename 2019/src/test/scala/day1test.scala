package adventofcode2019

import org.scalatest._

class day1test extends FunSuite
                      with BeforeAndAfterAll
                      with Matchers {

  test("Mass of 12") {
    Day1.requiredFuel(12) should be (2)
  }

  test("Mass of 14") {
    Day1.requiredFuel(14) should be (2)
  }

  test("Mass of 1969") {
    Day1.requiredFuel(1969) should be (654)
  }

  test("Mass of 100756") {
    Day1.requiredFuel(100756) should be (33583)
  }

  test("Module + fuel Mass of 1969") {
    Day1.moduleFuel(1969, Nil) should be (966)
  }

  test("Module + fuel Mass of 100756") {
    Day1.moduleFuel(100756, Nil) should be (50346)
  }
}

