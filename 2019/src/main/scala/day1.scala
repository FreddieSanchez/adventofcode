package adventofcode2019

import scala.io.Source

object Day1 {

  def requiredFuel(moduleMass: Int): Int = moduleMass / 3 - 2

  def moduleFuel(moduleMass: Int, fuelRequirements: List[Int]): Int =
    if (moduleMass <= 0) {
      fuelRequirements.sum
     } else  {
      val fuel = requiredFuel(moduleMass)
      val requirements = if (fuel > 0) fuel::fuelRequirements else fuelRequirements
      moduleFuel(fuel, requirements)
    }

  def moduleFuel1(moduleFuel: Int): Int = ???

  def part1(masses: List[Int]): Int = {
    masses.map(requiredFuel).sum
  }

  def part2(masses: List[Int]): Int = {
    masses.map(mass => moduleFuel(mass, Nil)).sum
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/1.input").getLines.toList.map(_.toInt)
    println("Part One : " + part1(lines))
    println("Part Two : " + part2(lines))
  }

}
