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
  case class Inc(amount: Int) extends Operation
  case class Dec(amount: Int) extends Operation
  

  sealed case class Expression(register: Register, condition:Condition, value: Integer)

  case class Instruction(register: Register, operation: Operation, expression: Expression) 

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
        Instruction(name, Inc(value.toInt), 
                   Expression(conditionRegName, readOperation(operation).get, condtionalValue.toInt))
      case regex(name, "dec", value, conditionRegName, operation, condtionalValue) =>
        Instruction(name,Dec(value.toInt), 
                   Expression(conditionRegName, readOperation(operation).get, condtionalValue.toInt))
    });

    parsed
  }
  
  def evaluateExpression(expression: Expression, registers: Registers): Boolean = {

    val registerValue = registers.getOrElse(expression.register,0)
    val expressionResult = expression.condition match  {
      case NotEqual => registerValue != expression.value 
      case Equal   => registerValue == expression.value
      case GreaterThan => registerValue > expression.value 
      case LessThan   => registerValue < expression.value 
      case GreaterOrEqual => registerValue >= expression.value 
      case LessOrEqual => registerValue <= expression.value 
    }
    expressionResult 
  }

  def doInstruction(instruction: Instruction, registers: Registers): Registers = 
  {
    val newRegisterValue : Int = instruction.operation match {
      case Inc(amount) => registers.getOrElse(instruction.register,0) + 
      (if (evaluateExpression(instruction.expression,registers) )
         amount 
       else 
         0)

      case Dec(amount) =>  registers.getOrElse(instruction.register,0) - 
      (if (evaluateExpression(instruction.expression,registers) )
         amount 
       else 
         0)
    }
    
    val ms = registers - instruction.register
    ms + (instruction.register -> newRegisterValue) 
  }

  def Run(instructions: Instructions) : Registers =
  {
    instructions.foldLeft(Map[Register,Int]()) {
      case (registers, instruction) => doInstruction(instruction, registers)
    }
  }
    
  def Run2(instructions: Instructions) : List[Registers] =
  {
    instructions.foldLeft(List[Map[Register,Int]](Map[Register,Int]())) {
      case (lstRegisters, instruction) => {
        (doInstruction(instruction, lstRegisters.last)::lstRegisters).reverse
      }
    }
  }

  def part1(instructions: Instructions) = Run(instructions).values.max
  def part2(instructions: Instructions) = Run2(instructions).map(reg => if (reg.values.isEmpty) Int.MinValue else reg.values.max).max
    
  def main(args: Array[String]): Unit = {

    val instructions = parse(io.Source.fromFile("./src/main/scala/8.input").getLines.toList)

    println("Part One: %d".format(part1(instructions)))
    println("Part Two: %d".format(part2(instructions)))
  }
  
}
