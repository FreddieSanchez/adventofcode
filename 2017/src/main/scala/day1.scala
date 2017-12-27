package adventofcode2017
import scala.io.Source 
object Day1 {

  def compress(nums:List[Int]): List[List[Int]] = {
    def _compress(acum: List[List[Int]], lst: List[Int]) : List[List[Int]] =
      lst match {
        case Nil => acum
        case x::xs => 
           if ( acum.isEmpty || acum.last.head != x ) 
              _compress(acum:::List(List(x)), xs)
           else
             _compress(acum.init:::List(acum.last:::List(x)), xs)
      }
    _compress(List(),nums)
  }

  def encode(nums:List[Int]):List[(Int, Int)] = {
    compress(nums).map ( xs => (xs.head, xs.length) )
  }

  def captchaNext(nums: List[Int]): Int  = {
     encode(nums).foldLeft(0)( (sum, current) => if (current._2 > 1) sum + (current._1)*(current._2-1) else sum)
  }
     
  def part1Captcha(nums: List[Int]): Int = {
   val value = captchaNext(nums)
 
   if (nums.last == nums.head)
     value + nums.last
   else 
     value
	}

  def splitAndZip(nums:List[Int]) : List[Int] = {
    val split = nums.splitAt(nums.length / 2)
    split._1.zip(split._2).flatMap( x => List(x._1, x._2))
  }


  def part2Captcha(nums: List[Int]): Int = {

   val split = splitAndZip(nums);
   val splitInHalf = nums.splitAt(nums.length / 2 )
   val lastHalfFirst = splitInHalf._2 ::: splitInHalf._1
   val split2 = splitAndZip(lastHalfFirst)
   val value = captchaNext(split) + captchaNext(split2)

   if (nums.last == nums(nums.length / 2) - 1) {
     value + nums.last
   }
   else {
     value
   }
	}

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/1.input").getLines.toList
    val nums = lines.flatMap(x => x).map(_.asDigit)
    println("Part One: " + part1Captcha(nums))
    println("Part Two: " + part2Captcha(nums))
  }
  
}
