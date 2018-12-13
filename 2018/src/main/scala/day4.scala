package adventofcode2018

import scala.io.Source 
import com.github.nscala_time.time.Imports._
import scala.util.Try

object Day4 {


  case class Guard(num:Int)

  trait Event {
    val time:DateTime
    val guard:Option[Guard]
    
  }
  case class WakesUp(time:DateTime, guard:Option[Guard] = None) extends Event
  case class FallsAsleep(time:DateTime, guard:Option[Guard] = None) extends Event
  case class ShiftBegins(time:DateTime, guard:Option[Guard]) extends Event

  def events(cs:List[String]):List[Event] = 
    cs.foldLeft(Nil:List[Event])(
    {
      case (lst:List[Event], str:String) => {

      //[1518-08-02 00:49] falls asleep
      //[1518-08-02 00:49] wakes up 
      //[1518-08-02 00:49] Guard #23324 begins shift 

      val pattern = raw"\[(\d+)-(\d+)-(\d+) (\d+):(\d+)\] (falls asleep|wakes up|Guard #(\d+) begins shift)".r
      str match {
        case pattern(y, mm, d, h, m, s, g) => 
        {
          val year   = y.toInt
          val month  = mm.toInt
          val day    = d.toInt
          val hour   = h.toInt
          val minute = m.toInt
          val guard  = Try(g.toInt).getOrElse(0)


          val time = DateTime.now() 
              .withYear(year)
              .withMonth(month)
              .withDay(day)
              .withHour(hour)
              .withMinute(minute)
              .withSecond(0)
          s match {
            case "falls asleep" => FallsAsleep(time)::lst
            case "wakes up" => WakesUp(time)::lst
            case _ => ShiftBegins(time , Some(Guard(guard)))::lst
          }
        }
      }
    }
    })



  def parse(cs:List[String]) = {
    def update(sorted:List[Event], acc:List[Event], guard:Guard): List[Event] = {
      sorted match {
        case Nil => acc
        case ShiftBegins(t,Some(g))::tail => update(tail, ShiftBegins(t, Some(g))::acc, g)
        case FallsAsleep(t, _)::tail => update(tail, FallsAsleep(t, Some(guard))::acc, guard)
        case WakesUp(t, _)::tail => update(tail, WakesUp(t, Some(guard))::acc, guard)
      }
    }
    // sort the events
    val sorted  = events(cs).sortWith((x,y) => x.time < y.time)

    // update the events to include the gaurd
    val updated = update(sorted, Nil, sorted.dropWhile( x => !x.isInstanceOf[ShiftBegins]).head match { case ShiftBegins(_,Some(g)) => g }).reverse

    // group the events by guard
    val grouped = updated.groupBy(x => x.guard).map { case(Some(x), xs) => (x,xs.filter(!_.isInstanceOf[ShiftBegins])) } 

    grouped
  }

  def minutesAsleep(guard:Guard, events: List[Event]) =  {
    val times = events.grouped(2).map( { case x::y::xs => (x.time to y.time).millis / 1000 / 60 } )
    times.foldLeft(0:Long)(_+_)
  }

  def sleepHistogram(guard:Guard, events:List[Event]):Map[Int,Int] = {
    val times = events.grouped(2)
    val histo = times.foldLeft(Map.empty:Map[Int, Int])({ 
      case (m, x::y::Nil) =>
      {
        (x.time.minute.get to  y.time.minute.get - 1).foldLeft(m)( (mm, minute) => 
        {
          val times = mm.getOrElse(minute, 0) + 1
          mm.updated(minute, times)
        })
      }
    })
    histo
  }

  def part1(grouped:Map[Guard, List[Event]]):Int = {
   val timeSlept:Set[(Guard, Long)] = grouped.keySet.map( x => (x, minutesAsleep(x, grouped(x))))
   val gaurdAsleepMost: Guard = timeSlept.maxBy(_._2)._1
   val histo : Map[Int,Int] = sleepHistogram(gaurdAsleepMost, grouped(gaurdAsleepMost))
   val min: Int =  histo.maxBy({case (key,value) => value})._1

   gaurdAsleepMost.num * min
  }

  /*
   * -- Part Two ---
   *  Strategy 2: Of all guards, which guard is most frequently asleep on the same minute?
   *
   *  In the example above, Guard #99 spent minute 45 asleep more than any other guard or minute - three times in total. (In all other cases, any guard spent any minute asleep at most twice.)
   *
   *  What is the ID of the guard you chose multiplied by the minute you chose? (In the above example, the answer would be 99 * 45 = 4455.)
   */
  def part2(grouped:Map[Guard, List[Event]]):Int= {

    val guards :Set[Guard] = grouped.keySet
    val guardHiso: Map[Guard, Map[Int, Int]] =
      guards
        .map(g => (g ,sleepHistogram(g, grouped(g))))
        .toMap

        println(guardHiso)
    val guard:Guard = guardHiso.maxBy({
      case (guard, histo) => 
        if (!histo.isEmpty)  {
          val x = histo.maxBy({case(k,v) => v})
          println(x)
          x._2
        }
       else 0})._1
    val histo : Map[Int,Int] = sleepHistogram(guard, grouped(guard))
    val min: Int =  histo.maxBy({case (key,value) => value})._1

    println(guard)
    println(min)
    guard.num * min

  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("./src/main/scala/4.input").getLines.toList
    val parsed:Map[Guard, List[Event]] = parse(lines)

    val lines1 = """[1518-11-01 00:00] Guard #10 begins shift
                    |[1518-11-01 00:05] falls asleep
                    |[1518-11-01 00:25] wakes up
                    |[1518-11-01 00:30] falls asleep
                    |[1518-11-01 00:55] wakes up
                    |[1518-11-01 23:58] Guard #99 begins shift
                    |[1518-11-02 00:40] falls asleep
                    |[1518-11-02 00:50] wakes up
                    |[1518-11-03 00:05] Guard #10 begins shift
                    |[1518-11-03 00:24] falls asleep
                    |[1518-11-03 00:29] wakes up
                    |[1518-11-04 00:02] Guard #99 begins shift
                    |[1518-11-04 00:36] falls asleep
                    |[1518-11-04 00:46] wakes up
                    |[1518-11-05 00:03] Guard #99 begins shift
                    |[1518-11-05 00:45] falls asleep
                    |[1518-11-05 00:55] wakes up""".stripMargin.split("\n").toList
    val parsed1:Map[Guard, List[Event]] = parse(lines1)
    println("Part One : " + part1(parsed))
    println("Part One : " + part1(parsed1))
    println("Part Two : " + part2(parsed))
    println("Part Two : " + part2(parsed1))
  }

}

