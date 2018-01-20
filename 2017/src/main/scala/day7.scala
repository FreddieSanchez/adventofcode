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
  def calcChildrenWeights(program:Program):Int ={
    def _findChildrenWeights(program:Program, accum:Int):Int = {
      if (program.children.isEmpty) accum
      else {
        val weights = for {
          childName <- program.children.toList
          child     =  programByName(childName)
          weight    = _findChildrenWeights(child,child.weight + accum)
        } yield (weight)
        weights.sum
      }
    }
    val weight = _findChildrenWeights(program,0)
    weight
  }


  // Map the program name to the weight of it's children
  lazy val programToTotalWeight = programs.map(program => 
                                               program.name -> calcChildrenWeights(program)).toMap

  def childrenWeight(program:Program):Set[(Program,Int)] =
    program.children.map(programByName)
                    .map( program => 
                         (program, program.weight + programToTotalWeight(program.name)))


  def unBalancedChild(program:Program):Option[Program] = {
    val weights = childrenWeight(program)
    //group by them by their weight
    val grouped = weights.groupBy(_._2)
    if (grouped.size == 1){
      println(grouped)
      None
    }
    else {
      println(grouped.filter({case (weight, children) => children.size == 1}))
      //get the child that doesn't match the others.
      val (outcast) = grouped.filter({case (weight, children) => children.size == 1})
                             .toSeq.head._2.toSeq.head._1
     Some(outcast)
    }
  }


  def part2(program:Program, parent:Option[Program]):Int = {
    println(program)
    val unbalanced = unBalancedChild(program)
    println(unbalanced)
    unbalanced match {
      case Some(prog) => part2(prog,Some(program))
      case None => 
        // Childern are balanced, parent that needs to be rebalanced
      {
        println(childrenWeight(parent.get).groupBy(_._2))
        val offBy = childrenWeight(parent.get).groupBy(_._2).map( { case (k,v) => k }).reduce(_ - _)
        println(offBy)
        val outcast = unBalancedChild(parent.get)
        println(outcast)
        outcast.get.weight + offBy
      }
    }

  }
  



//  def part2(programs:Programs, root: Program) = {
//    def loop(program:Program):(Program, List[Int]) = {
//      val blanaced, weights = isBalanced(programs, program)
//      if (!balanced) {
//        val children = program.children.map(programByName))
//        children.flatMap(loop)
//      } else  
//
//    }
//  }

  def main(args: Array[String]): Unit = {

    val root = part1
    println("Part One: " + root)
    println("Part Two: " + part2(root, None))
  }
  
}
