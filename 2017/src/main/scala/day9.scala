package adventofcode2017

object Day9 {
 
  def part1(stream: List[Char]):Int = {
    @scala.annotation.tailrec
    def _parse(str: List[Char], currentScore: Int, totalScore:Int, inGarbage: Boolean ): Int = {
      //println("Str: %s, currentscore: %d, totalScore: %d, inGarbage: %b".format(str,currentScore,totalScore,inGarbage))
      str match  {
        case ','::xs => _parse(xs, currentScore,totalScore,inGarbage)
        case '}'::xs if (!inGarbage) => _parse(xs, currentScore - 1, totalScore + currentScore, inGarbage)
        case '{'::xs if (!inGarbage) => _parse(xs, currentScore + 1, totalScore, inGarbage)
        case '<'::xs if (inGarbage)  => _parse(xs, currentScore, totalScore, inGarbage)
        case '<'::xs if (!inGarbage) => _parse(xs, currentScore, totalScore, !inGarbage)
        case '>'::xs if (inGarbage) => _parse(xs, currentScore, totalScore, !inGarbage)
        case '!'::_::xs if (inGarbage) => _parse(xs, currentScore, totalScore, inGarbage)
        case _::xs if (inGarbage) => _parse(xs,currentScore,totalScore,inGarbage)
        case nil => totalScore
       }
    }

    _parse(stream,0,0,false)

  }

  
  def part2(stream: List[Char]):List[Char] = {
    @scala.annotation.tailrec
    def _parse(str: List[Char], garbageChar:List[Char], inGarbage: Boolean ): List[Char] = {
      //println("Str: %s, garbage: %s,  inGarbage: %b".format(str,garbageChar,inGarbage))
      str match  {
        case ','::xs if (!inGarbage)=> _parse(xs, garbageChar ,inGarbage)
        case '}'::xs if (!inGarbage) => _parse(xs, garbageChar, inGarbage)
        case '{'::xs if (!inGarbage) => _parse(xs, garbageChar, inGarbage)
        case '<'::xs if (!inGarbage) => _parse(xs, garbageChar, !inGarbage)
        case '<'::xs if (inGarbage)  => _parse(xs, '<'::garbageChar, inGarbage)
        case '>'::xs if (inGarbage) => _parse(xs, garbageChar, !inGarbage)
        case '!'::_::xs if (inGarbage) => _parse(xs, garbageChar, inGarbage)
        case x::xs if (inGarbage) => _parse(xs, x::garbageChar,inGarbage)
        case nil => garbageChar
       }
    }

    _parse(stream,List(),false)

  }
  def main(args: Array[String]): Unit = {
    val input = io.Source.fromFile("./src/main/scala/9.input").getLines.mkString.toList
    val instructions = part1(input)
    val nonGarbageChar = part2(input).length
    println("Part One: %d".format(instructions))
    println("Part Two: %d".format(nonGarbageChar))
  }

}
