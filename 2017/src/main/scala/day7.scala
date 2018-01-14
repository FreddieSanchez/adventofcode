package adventofcode2017

object Day7 {

  type Name = String;
  type Programs = Set[Program]

  case class Program(name:Name, weight: Int, children:Set[Name])


  
  def parse(lines: List[String]):Programs = {
    val regex = """(\w+) \((\d+)\)( -> (.*))?""".r

    val programs = lines.map({
      case regex(name, weight, _, children) => 
        Some(Program(name, weight.toInt, Option(children).map(_.split(", ").toSet).getOrElse(Set())))
      case _ =>  None
    }).toSet
    
    programs.collect({ case Some(x) => x })
  }
  

  def bottomProgram(programs: Programs):Option[Program]= {
    val bottom = programs.filter(program => programs.forall( prog => !prog.children.contains(program.name)))
    Option(bottom.head)
  }

  def part1(programs: Programs):Option[Program] = {
    bottomProgram(programs)
  }
  def childByName(programs:Programs, name:Name):Option[Program] = 
    programs.find(x => x.name == name)


  def childWeights(programs:Programs, program:Program) : List[Int] = {
    val children = program.children.toList.map(
      c => {
        val child = childByName(programs, c).getOrElse(Program("Not Found", 0, Set()))
        sumChildrenWeights(programs,child,child.weight)
      })
    children
  }

  def sumChildrenWeights(programs:Programs, program:Program, sum:Int):Int = {
    if (program.children.size == 0) sum
    else {
      val total = childWeights(programs,program).sum
      sum + total
    }
  }

  def isBalanced(programs: Programs, program:Program):Boolean = {
    println(program)

    val weights = childWeights(programs,program)
    println(weights)
    weights.groupBy(s => s).size == 1
  }


  def part2(root: Program) = {


    

    
    
    

    

  }

  def main(args: Array[String]): Unit = {
    val lines = io.Source.fromFile("./src/main/scala/7.input").getLines.toList
    val parsed = parse(lines)
    val root = part1(parsed)
    println("Part One: " + root)
    //println("Part Two: " + part2(parsed)) 
  }
  
}
