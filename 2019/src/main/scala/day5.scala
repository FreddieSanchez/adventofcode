package adventofcode2019

import scala.io.Source
import enumeratum.values._
import adventofcode2019.Day5.Mode.PositionMode
import adventofcode2019.Day5.Mode.ImmediateMode

object Day5 {

  sealed trait Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer
    def calculate(ints: List[Int]): List[Int]
  }

  case class Add(p1: Parameter, p2: Parameter, p3: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
     pointer.copy(address = pointer.address + 4)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      val result = p1.get(ints) + p2.get(ints)
      println(s"Saved $result @ position ${p3.put(ints)}")
      ints.updated(p3.put(ints), result)
    }
  }

  case class Multiply(p1: Parameter, p2: Parameter, p3: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
     pointer.copy(address = pointer.address + 4)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      val result = p1.get(ints) * p2.get(ints)
      ints.updated(p3.put(ints), result)
    }
  }

  case class Save(p1: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
     pointer.copy(address = pointer.address + 2)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      val value = readInt
      println(s"Replaced - ${p1.get(ints)} with $value @ position ${p1.put(ints)}")
      ints.updated(p1.put(ints), value)
    }
  }

  case class Echo(p1: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
     pointer.copy(address = pointer.address + 2)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      println(p1.get(ints))
      ints
    }
  }
   case class JumpIfTrue(p1: Parameter, p2: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
      if (p1.get(ints) != 0)
        pointer.copy(address = p2.get(ints))
      else
        pointer.copy(address = pointer.address + 3)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      println(p1.get(ints))
      ints
    }
   }
   case class JumpIfFalse(p1: Parameter, p2: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
      if (p1.get(ints) == 0)
        pointer.copy(address = p2.get(ints))
      else
        pointer.copy(address = pointer.address + 3)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      println(p1.get(ints))
      ints
    }
   }
   case class LessThan(p1: Parameter, p2: Parameter, p3: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
        pointer.copy(address = pointer.address + 4)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      if (p1.get(ints) < p2.get(ints))
        ints.updated(p3.put(ints), 1)
      else
        ints.updated(p3.put(ints), 0)
    }
   }
   case class Equals(p1: Parameter, p2: Parameter, p3: Parameter) extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
        pointer.copy(address = pointer.address + 4)
    }
    def calculate(ints: List[Int]): List[Int] =  {
      if (p1.get(ints) == p2.get(ints))
        ints.updated(p3.put(ints), 1)
      else
        ints.updated(p3.put(ints), 0)
    }
  }

  case class Halt() extends Instruction {
    def nextInstruction(ints: List[Int], pointer: InstructionPointer): InstructionPointer = {
      pointer
    }
    def calculate(ints: List[Int]): List[Int] =  {
      println("HALTED")
      ints
    }
  }
  sealed abstract class Mode(val value: Int, val name: String) extends IntEnumEntry
  case object Mode extends IntEnum[Mode] {
    import Position._
    case object PositionMode extends Mode(value = 0, name = "Position")
    case object ImmediateMode extends  Mode(value = 1, name = "Immediate")

    def apply(int: Int, position: Position): Mode = {
      val opcode = "%05d".format(int)
      position match {
        case FirstPosition => Mode.withValue(opcode.drop(2).take(1).toInt)
        case SecondPosition => Mode.withValue(opcode.drop(1).take(1).toInt)
        case ThirdPosition => Mode.withValue(opcode.drop(0).take(1).toInt)
      }
    }
    val values = findValues
  }

  sealed abstract class Position(val value: Int, val name: String) extends IntEnumEntry
  object Position extends IntEnum[Position] {
    case object FirstPosition extends Position(value = 1, name = "First")
    case object SecondPosition extends  Position(value = 2, name = "Second")
    case object ThirdPosition extends  Position(value = 3, name = "Third")
    val values = findValues
  }

  case class Parameter(address: Int, mode: Mode) {
    def get(ints: List[Int]): Int = {
      mode match {
        case PositionMode =>
          println(s"Position Mode @address: $address: ${ints(ints(address))}")
          ints(ints(address))
        case ImmediateMode =>
          println(s"Immediate Mode @ address: $address: ${ints(address)}")
          ints(address)
      }
    }
    def put(ints: List[Int]): Int = {
      mode match {
        case PositionMode =>
          ints(address)
        case ImmediateMode =>
          ???
      }
    }
  }

  case class InstructionPointer(address: Int) {
    def get(ints: List[Int]): Int =
      ints(address)
  }
  object Instruction {
    import Position._

    def apply(pointer: InstructionPointer, ints: List[Int]): Instruction = {
      println(s">>> $pointer")
      val opcode = "%05d".format(pointer.get(ints))
      opcode.drop(3).take(2) match {
        case "01" =>
          println("Add Instruction")
          Add(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)),
          Parameter(pointer.address + 2, Mode(pointer.get(ints), SecondPosition)),
          Parameter(pointer.address + 3, Mode(pointer.get(ints), ThirdPosition)))
        case "02" =>
          println("Multiply Instruction")
          Multiply(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)),
          Parameter(pointer.address + 2, Mode(pointer.get(ints), SecondPosition)),
          Parameter(pointer.address + 3, Mode(pointer.get(ints), ThirdPosition)))
        case "03" =>
          println("Save Instruction")
          Save(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)))
        case "04" =>
          println("Echo Instruction")
          Echo(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)))

        case "05" =>
          println("Jump If True Instruction")
          JumpIfTrue(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)),
          Parameter(pointer.address + 2, Mode(pointer.get(ints), SecondPosition)))
        case "06" =>
          println("Jump If False Instruction")
          JumpIfFalse(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)),
          Parameter(pointer.address + 2, Mode(pointer.get(ints), SecondPosition)))
        case "07" =>
          println("Less Than Instruction")
          LessThan(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)),
          Parameter(pointer.address + 2, Mode(pointer.get(ints), SecondPosition)),
          Parameter(pointer.address + 3, Mode(pointer.get(ints), ThirdPosition)))
        case "08" =>
          println("Equals Instruction")
          Equals(Parameter(pointer.address + 1, Mode(pointer.get(ints), FirstPosition)),
          Parameter(pointer.address + 2, Mode(pointer.get(ints), SecondPosition)),
          Parameter(pointer.address + 3, Mode(pointer.get(ints), ThirdPosition)))
        case "99" => Halt()
      }
    }
  }

  object IntCodeRunner {
    private def run(intCode: List[Int], pointer: InstructionPointer): List[Int]= {
      val instruction = Instruction(pointer, intCode)
      val result = instruction.calculate(intCode)
      instruction match {
        case _: Halt => intCode
        case _ => run(result, instruction.nextInstruction(intCode, pointer))
      }
    }

    def run(intCode: List[Int]): List[Int]=
      run(intCode, InstructionPointer(0))
  }
  def part1(ints: List[Int]): List[Int] = {
    IntCodeRunner.run(ints)
  }

  def part2(ints: List[Int]): List[Int] = {
    IntCodeRunner.run(ints)
  }
  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/5.input").getLines.toList.head.split(",").map(_.toInt).toList
    println("Part One : " + part1(lines))
    println("Part Two : " + part2(lines))
  }

}
