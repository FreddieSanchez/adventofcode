package adventofcode2017

object Day9 {
 
  def part1(stream: List[Char]) = {
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

  

  def main(args: Array[String]): Unit = {
    val input = io.Source.fromFile("./src/main/scala/9.input").getLines.mkString.toList
    val instructions = part1(input)
    println("Part One: %d".format(instructions))
  }

}
