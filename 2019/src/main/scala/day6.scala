package adventofcode2019

import scala.io.Source
import enumeratum.values._

object Day6 {
  // o1 orbits o2
  case class OrbitalObject(name: String) extends AnyVal
  object OrbitalObject {
    def orbit(str: String): (OrbitalObject, OrbitalObject) = {

      val parts = str.split("\\)").toList
      println(s"$str, $parts")
      (OrbitalObject(parts.head),OrbitalObject(parts.tail.head))
    }
  }
  case class Orbits(orbits: Map[OrbitalObject, List[OrbitalObject]], orbitees: Map[OrbitalObject, OrbitalObject]) {

    def depth(root: OrbitalObject): Int = {
      def _depth(obj: OrbitalObject, depth: Int): List[Int]= {
        if (orbits.contains(obj)) {
          depth::orbits(obj).flatMap(child => _depth(child, depth + 1))
        } else
          List(depth)
      }
      _depth(root, 0).sum
    }

    def transfers(start: OrbitalObject, end: OrbitalObject): Int = {
      def buildPath(obj: OrbitalObject, path: Map[OrbitalObject, OrbitalObject], acc: List[OrbitalObject]): List[OrbitalObject] = {
        if( obj == start)
          acc.reverse
        else
          buildPath(path(obj), path, obj :: acc)
      }
      def _transfers(objs: List[OrbitalObject], visited: Set[OrbitalObject], path: Map[OrbitalObject, OrbitalObject]): List[OrbitalObject] = {
        if (objs.nonEmpty) {
          val obj = objs.head
          if (obj == end) {
            println("Found!")
            buildPath(end, path, Nil)
          } else {
            val neighbors: List[OrbitalObject] = List(orbits.get(obj), orbitees.get(obj).map(List(_))).flatten.flatten.distinct.filterNot( x => visited.contains(x))
            val previous =  neighbors.map((_, obj)).toMap ++ path
            _transfers(objs.tail ::: neighbors, visited + obj, previous)
          }
        } else {
          Nil
        }
      }

      val path = _transfers(List(start), Set.empty, Map.empty)
      println(path)
      path.length - 2
    }

  }

  object Orbits {
    def apply(str: List[String]): Orbits = {
      val originalOrbites = str.map(OrbitalObject.orbit(_))
      val orbits = originalOrbites.groupBy(_._1).mapValues(_.map(_._2))
      val orbitees = originalOrbites.groupBy(_._2).mapValues(_.map(_._1).head)
      Orbits(orbits, orbitees)
    }
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/6.input").getLines.toList
    val orbits = Orbits(lines)
    val depth = orbits.depth(OrbitalObject("COM"))
    val transfers = orbits.transfers(OrbitalObject("YOU"), OrbitalObject("SAN"))
    println(s"Part 1: total orbits: $depth")
    println(s"Part 2: total transfers: $transfers")
  }

}
