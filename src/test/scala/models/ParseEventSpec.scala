package models

import models.ParseEvent._
import models.helpers.TestHelper
import org.scalatest.Assertion
class ParseEventSpec extends TestHelper {

  private val exampleData: List[Int] = List(0x781002, 0xf0101f, 0x1310c8a1, 0x29f981a2, 0x48332327)

  "convertToEvent" should {
    "turn hexadecimal value into an Event" in {
      val hexadecimalValue = 0x781002
      val event: Event = convertToEvent(hexadecimalValue)
      val twoPointsScored = 2
      val team2Total = 0
      val team1Total = 2
      val secondsOfPlay = 15

      makeTests(twoPointsScored, Team1, secondsOfPlay)(event)
      event.team2Total shouldBe team2Total
      event.team1Total shouldBe team1Total
    }
  }

  "feedData" should {
    "convert the hexadecimal values in the bit pattern specification to the expected events" when {

      val events = feedData(exampleData)

      "parsing the first event" in {
        val firstEvent = events.values.head
        makeTests(2, Team1, 15)(firstEvent)
      }
      "parsing the second event" in {
        val secondEvent: Event = events.values.tail.head
        makeTests(3, Team2, 30)(secondEvent)
      }
    }

//      "parsing the third event" in {
//        val thirdEvent: Event = events.values.take(3).head
//        val differenceInPoints = Math.abs(thirdEvent.team1Total - thirdEvent.team2Total)
//
//        thirdEvent.pointsScored shouldBe 1
//        thirdEvent.whoScored shouldBe Team1
//        differenceInPoints shouldBe 5
//      }
//      "parsing the fourth event" in {
//        set.take(4).head shouldBe 0x29f981a2
//        println(set.take(4).head.toBinaryString)
//        val fourthEvent: Event = events.values.take(4).head
//        val differenceInPoints = Math.abs(fourthEvent.team1Total - fourthEvent.team2Total)
//
//        fourthEvent.pointsScored shouldBe 2
//        fourthEvent.whoScored shouldBe Team1
//        differenceInPoints shouldBe 4
//      }
    }

    def makeTests(pointsScored: Int, whoScored: Team, elapsedTime: Int): Event => Assertion =
      event => {
        event.pointsScored shouldBe pointsScored
        event.whoScored shouldBe whoScored
        event.elapsedMatchTime shouldBe elapsedTime

  }
}
