package adventofcode2017.test

import org.scalatest.FunSuite
import adventofcode2017._
import adventofcode2017.Day4._

class Day4 extends FunSuite {

  test("part1 - valid passphrase") {
    assert(validPassphrase(List("aa","bb","cc","dd","ee")))
  }

  test("part1 - valid passphrase2") {
    assert(validPassphrase(List("aa","bb","cc","dd","")))
  }
  
  test("part1 - invalid passphrase2") {
    assert(!validPassphrase(List("aa","bb","cc","dd","aa")))
  }
  test("part2 - valid passphrase") {
    assert(validNonAnagramPassphrase(List("abcde","fghij")))
  }

  test("part2 - invalid passphrase2") {
    assert(!validNonAnagramPassphrase(List("abcde","xyz","ecdab")))
  }
  
  test("part2 - valid passphrase2") {
    assert(validPassphrase(List("a","ab","abc","abd","abf","abj")))
  }
  test("part2 - valid 2nd passphrase2") {
    assert(validPassphrase(List("iiii","oiii","ooii","oooi","oooo")))
  }
 
  test("part2 - invalid 2nd passphrase2") {
    assert(!validNonAnagramPassphrase(List("oiii","ioii","iioi","iiio")))
  }
}


