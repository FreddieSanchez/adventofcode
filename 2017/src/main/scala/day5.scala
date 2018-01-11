package adventofcode2017

object Day5 {

  @annotation.tailrec
  def solve(instructions:Vector[Int], index:Int, jumps:Int, fn:Int => Int):Int = {
    if (index < 0 || index >= instructions.length) jumps
    else  {
      val offset = instructions(index)
      val updatedInstructions = instructions.updated(index,  fn(offset))
      solve(updatedInstructions, index + offset, jumps + 1, fn)
    }


  }
  def part1(instructions:Vector[Int]):Int = {
    solve(instructions,0,0, {x => x + 1})
  }

  def part2(instructions:Vector[Int]):Int = {
    solve(instructions,0,0, {x => if (x >=3) x - 1 else x + 1})
  }


  def main(args: Array[String]): Unit = {
    def parseInput(lines: Vector[String]): Vector[Int]=  lines.map(_.toInt)

    val lines = io.Source.fromFile("./src/main/scala/5.input").getLines.toVector
    val parsed = parseInput(lines)
    println("Part One: " + part1(parsed))
    println("Part Two: " + part2(parsed)) 
  }
  
}
