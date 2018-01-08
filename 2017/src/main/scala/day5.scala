package adventofcode2017

object Day5 {


  def jump(instructions:List[Int], index:Int):(List[Int],Int) =  {
    val offset = instructions(index)
    val updatedInstructions = instructions.updated(index,  offset + 1)
    (updatedInstructions,offset)
  }

  def solvePart1(instructions:List[Int], index:Int, jumps:Int):Int = {
    if (index >= instructions.length) jumps
    else  {
      val ( updatedInstructions, nextIndex ) = jump(instructions, index)
      solvePart1(updatedInstructions, index + nextIndex, jumps+1)
    }


  }
  def part1(instructions:List[Int]):Int = {
    solvePart1(instructions,0,0)
  }


  def part2(instructions:List[Int]):Int = {
    ???
  }


  def main(args: Array[String]): Unit = {
    def parseInput(lines: List[String]): List[Int]=  lines.map(_.toInt)

    val lines = io.Source.fromFile("./src/main/scala/5.input").getLines.toList
    val parsed = parseInput(lines)
    println("Part One: " + part1(parsed))
    println("Part Two: " + part2(parsed)) 
  }
  
}
