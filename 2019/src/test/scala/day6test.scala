package adventofcode2019

import org.scalatest._
import adventofcode2019.Day6.OrbitalObject
import adventofcode2019.Day6.Orbits

class Day6Test extends FunSuite
                      with BeforeAndAfterAll
                      with Matchers {


    test("Test Name") {
      val orbits = """COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L""".split(",").toList
      val os = Orbits(orbits)
      os.depth(OrbitalObject("COM")) should be (42)
    }


      test("test2") {
        val orbits = """COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L,K)YOU,I)SAN""".split(",").toList
        val os = Orbits(orbits)
        os.transfers(OrbitalObject("YOU"), OrbitalObject("SAN")) should be (4)
      }


}
