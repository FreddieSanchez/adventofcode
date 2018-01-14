package adventofcode2017

object Day7 {

  type Name = String;
  type Programs = List[Program]
  case class Program(name:Name, weight: Int, children:Set[Name])

  def parse(lines: List[String]):Programs = {
    val regex = """(\w+) \((\d+)\)( -> (.*))?""".r

    val programs = lines.map({
      case regex(name, weight, _, children) => 
        Program(name, weight.toInt, Option(children).map(_.split(", ").toSet).getOrElse(Set()))
    })
    
    programs
  }

  def bottomProgram(programs: Programs):Option[Program]= {
    val bottom = programs.filter(program => programs.forall( prog => !prog.children.contains(program.name)))
    Option(bottom.head)
  }
  def part1(programs: Programs):Option[Program] = {
    bottomProgram(programs)
  }
  def part2(lines: List[String]) = ???

  def main(args: Array[String]): Unit = {
    val lines = io.Source.fromFile("./src/main/scala/7.input").getLines.toList
    val parsed = parse(lines)
    println("Part One: " + part1(parsed))
    //println("Part Two: " + part2(parsed)) 
  }
  
}
