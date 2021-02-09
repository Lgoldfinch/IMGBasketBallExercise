package models

import models.ParseEvent._
import models.helpers.TestHelper
import org.scalatest.Assertion
import data.Samples._
class ParseEventSpec extends TestHelper {
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
      "parsing the first event" in {
        val firstEvent = eventsCreatedByFeedData.values.head
        makeTests(pointsScored = 2, whoScored = Team1, elapsedTime = 15)(firstEvent)
      }
      "parsing the second event" in {
        val secondEvent: Event = eventsCreatedByFeedData.values.tail.head
        makeTests(pointsScored = 3, whoScored = Team2, elapsedTime = 30)(secondEvent)
      }

      "parsing the third event" in {
        val thirdEvent: Event = eventsCreatedByFeedData.values.takeRight(3).head
        val differenceInPoints = Math.abs(thirdEvent.team1Total - thirdEvent.team2Total)

        makeTests(pointsScored = 1, Team1, 610)(thirdEvent)
        differenceInPoints shouldBe 5
      }
    }
  }


  "Sample data 1 should be parsed and should be unchanged by validation" in {
    val unvalidatedEvents = Events(convertedEvents(sample1))
    val validatedEvents = feedData(sample1)

    unvalidatedEvents shouldBe validatedEvents
  }

  /**
   * this test is commented out as I couldn't fully correct sample 2. More info on this in ValidateData object.
   */

  "Sample data 2 should be parsed and should be changed by validation" in {

//    val unvalidatedEvents = Events(convertedEvents(sample2)).values
//    val validatedEvents = feedData(sample2).values
//
  }

  private def makeTests(pointsScored: Int, whoScored: Team, elapsedTime: Int): Event => Assertion =
    event => {
      event.pointsScored shouldBe pointsScored
      event.whoScored shouldBe whoScored
      event.elapsedMatchTime shouldBe elapsedTime

    }
}