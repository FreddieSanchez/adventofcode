package adventofcode2017

object Day8 {

  type Register = String

  sealed trait Condition
  case object GreaterThan extends Condition
  case object LessThan extends Condition
  case object Equal extends Condition
  case object GreaterOrEqual extends Condition
  case object LessOrEqual extends Condition
  case object NotEqual extends Condition

  sealed trait Operation
  case class Inc(register: Register, amount: Int) extends Operation
  case class Dec(register: Register, amount: Int) extends Operation
  

  sealed case class Expression(register: Register, condition:Condition, value: Integer)

  case class Instruction(operation: Operation, expression: Expression) 

  type Instructions = List[Instruction] 

  type Registers = Map[Register,Int]

  def readOperation(op: String):Option[Condition] = 
    op match {
      case "!=" => Some(NotEqual)
      case "==" => Some(Equal)
      case ">" => Some(GreaterThan)
      case "<" => Some(LessThan)
      case ">=" => Some(GreaterOrEqual)
      case "<=" => Some(LessOrEqual)
      case _ => None
    }

  def parse(lines: List[String]):Instructions = {
    val regex = """(\w+) (\w+) (-?\d+) if (\w+) (!=|>=|==|<=|<|>) (-?\d+)""".r

    val parsed = lines.map({
      case regex(name, "inc", value, conditionRegName, operation, condtionalValue) =>
        Instruction(Inc(name, value.toInt), 
                   Expression(conditionRegName, readOperation(operation).get, condtionalValue.toInt))
      case regex(name, "dec", value, conditionRegName, operation, condtionalValue) =>
        Instruction(Dec(name, value.toInt), 
                   Expression(conditionRegName, readOperation(operation).get, condtionalValue.toInt))
    });

    parsed
  }

  // Todo - Define run function that processes all the instructions in the list.
  // the registers map will keep the current state
  def Run(instructions: Instructions) : Registers =
  ???
//  {
//    
//    var registers = instructions.fold(Map[Register,Int]()) {
//      (registers, instruction) => 
//      {
//        registers
//      }
//
//    }
//    registers
//  }

  def main(args: Array[String]): Unit = {

    var instructions = parse(io.Source.fromFile("./src/main/scala/8.input").getLines.toList)

    

    println("Part One: " + instructions)
  }
  
}
