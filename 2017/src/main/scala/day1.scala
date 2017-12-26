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

  def captcha(nums: List[Int]): Int = {
   val value = 
     encode(nums).foldLeft(0)( (sum, current) => if (current._2 > 1) sum + (current._1)*(current._2-1) else sum)
 
   if (nums.last == nums.head)
     value + nums.last
   else 
     value
	}
  
  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/1.input").getLines.toList
    val nums = lines.flatMap(x => x).map(_.asDigit)
    println("Part One: " + captcha(nums))
  }
  
}
