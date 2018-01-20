package adventofcode2017
import scala.collection.mutable.{Map => MMap}

object Day7 {

  type Name = String
  type Programs = Set[Program]

  case class Program(name:Name, weight: Int, children:Set[Name])

  var programs:Programs = parse(io.Source.fromFile("./src/main/scala/7.input").getLines.toList)
  lazy val programByName:Map[Name,Program] = programs.map(program => program.name -> program).toMap
  lazy val allChildren:Programs = programs.flatMap(_.children).map(programByName)
  
  def parse(lines: List[String]):Programs = {
    val regex = """(\w+) \((\d+)\)( -> (.*))?""".r

    val parsed = lines.map({
      case regex(name, weight, _, children) => 
        Program(name, weight.toInt, Option(children).map(_.split(", ").toSet).getOrElse(Set()))
    }).toSet

    parsed
  }

  lazy val bottomProgram:Option[Program]= {
    val bot = programs diff allChildren
    if (bot.size == 1) Option(bot.head) else None
  }

  lazy val part1: Program = bottomProgram.get

  // Un-memoized version to get the total of all the children weights, it will 
  // recompute the weights multiple times for the same program
  def calcWeights(program:Program):Int ={
    program.weight + program.children.map(programByName).toList.map(calcWeights).sum
  }


  // Map the program name to the weight of it's children
  lazy val programToTotalWeight = programs.map(program => 
                                               program -> calcWeights(program)).toMap

  def unBalancedChild(program:Program):Option[Program] = {
    val children = program.children.map(programByName).toList
    val weights = program.children.map(programByName).toList.map(programToTotalWeight)
    //group by them by their weight
    if (weights.toSet.size == 1){
      None
    }
    else {
      assert(weights.toSet.size == 2)
      
      val outcasts = children.zip(weights).groupBy(_._2).find({ case (k,v) => v.length == 1 })
      val outcast = outcasts match {
        case Some((_,List((prog,_)))) => prog
      }

      //get the child that doesn't match the others.
     Some(outcast)
    }
  }

  def part2(program:Program, parent:Option[Program]):Int = {
    val unbalanced = unBalancedChild(program)
    unbalanced match {
      case Some(prog) => part2(prog,Some(program))
      case None => 
        // Childern are balanced, parent that needs to be rebalanced
      {
        val weights = parent.get.children.map(programByName).toList.map(programToTotalWeight).toSet
        val offby = weights.reduce(_ - _)
        val outcast = unBalancedChild(parent.get)
        outcast.get.weight + offby
      }
    }

  }
  def main(args: Array[String]): Unit = {

    val root = part1
    println("Part One: " + root)
    println("Part Two: " + part2(root, None))
  }
  
}
