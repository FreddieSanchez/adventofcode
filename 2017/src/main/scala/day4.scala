package adventofcode2017

object Day4 {


  type Passphrase = List[String] 

  def validPassphrase(passphrase: Passphrase):Boolean = {
    passphrase.distinct.length == passphrase.length
  }

  def part1(passphrases:List[Passphrase]):Int = {
    passphrases.filter(validPassphrase).length
  }


  def validNonAnagramPassphrase(passphrase:Passphrase): Boolean = {
    passphrase.map(x => x.sorted).distinct.length == passphrase.length
  }

  def part2(passphrases:List[Passphrase]):Int = {
    passphrases.filter(validNonAnagramPassphrase).length
  }


  def main(args: Array[String]): Unit = {
    def parseInput(lines: List[String]): List[Passphrase]=  lines.map(x => x.split(" ").toList)

    val lines = io.Source.fromFile("./src/main/scala/4.input").getLines.toList
    val parsed = parseInput(lines)
    println("Part One: " + part1(parsed))
    println("Part Two: " + part2(parsed)) 
  }
  
}
