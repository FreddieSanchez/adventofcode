package adventofcode2017

object Day6 {

  def redistribute(banks:Vector[Int], index:Int): Vector[Int] = {

    def nextIndex(x:Int, vec:Vector[Int]):Int = if (x + 1 == vec.length) 0 else x + 1

    @annotation.tailrec
    def _redistribute(banks: Vector[Int], blocks:Int, index:Int): Vector[Int] = {
      if (blocks == 0) banks
      else {
        val curBankBlocks = banks(index)
        val updatedBanks  = banks.updated(index, curBankBlocks + 1)
        val _index = nextIndex(index, banks)
        _redistribute(updatedBanks, blocks - 1, _index)
      }
    }
    
    val blocks = banks(index)
    val _index = nextIndex(index, banks)
    _redistribute(banks.updated(index, 0), blocks, _index)
  }

  def largestBank(banks:Vector[Int]):Int = banks.indexOf(banks.max)

  def solvePart1(banks:Vector[Int]):Int = {
    @annotation.tailrec
    def _solvePart1(banks:Vector[Int], prevBanks:Set[Vector[Int]], times:Int):Int = {
      if (prevBanks.contains(banks)) times
      else {
        val redistributed = redistribute(banks,largestBank(banks))
        _solvePart1(redistributed, prevBanks + banks, times + 1)
      }
    }
    _solvePart1(banks, Set(), 0)
  }
  def part1(banks:Vector[Int]):Int = {
    solvePart1(banks)
  }

  def part2(instructions:Vector[Int]):Int = {
    ???
  }


  def main(args: Array[String]): Unit = {

    val lines = io.Source.fromFile("./src/main/scala/6.input").getLines.toVector.apply(0).split("\\s+").map(_.toInt).toVector
    println("Part One: " + part1(lines))
    println("Part Two: " + part2(lines)) 
  }
  
}
