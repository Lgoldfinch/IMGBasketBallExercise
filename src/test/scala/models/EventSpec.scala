package models

import models.ParseEvent._
import models.helpers.TestHelper

class EventSpec extends TestHelper {

  "Events" should {
    "return the match score when Team 1's total is higher than team Team 2's" in {
      val team1Score = 25
      val team2Score = 20
      event2.team1Total shouldBe team1Score
      event2.team2Total shouldBe team2Score

      twoEvents.getMatchScore shouldBe s"Team 1 leads, $team1Score-$team2Score"
    }

    "getLastEvent" in {
      twoEvents.getLastEvent shouldBe event2
    }

    "getLastEvents" should {
      "get final event in a list" in {
        val finalEventInList = convertToEvent(exampleDataFromTestSpec.last)

        eventsCreatedByFeedData.getLastEvents(1).values.head shouldBe finalEventInList
      }
      "get last two events in a list" in {
        val hexCode1 = 0x29f981a2
        val hexCode2 = 0x48332327
        val convert1 = convertToEvent(hexCode1)
        val convert2 = convertToEvent(hexCode2)
        val lastTwoEvents = List(convert1, convert2)

        eventsCreatedByFeedData.getLastEvents(2).values.take(2) shouldBe lastTwoEvents
      }
    }
  }
}
