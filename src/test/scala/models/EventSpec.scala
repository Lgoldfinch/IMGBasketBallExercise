package models

import models.helpers.TestHelper

class EventSpec extends TestHelper {

  "Event" should {
    "extract the correct value for points scored out of a binary string" in {
      val twoPointsScored = 2
      Event(exampleEventAsBinary1).pointsScored shouldBe twoPointsScored
    }
    "extract the correct value" when {
      "the binary slot for team who scored is 0" in {
        Event(exampleEventAsBinary1).whoScored shouldBe Team1
      }
      "the binary slot for team who scored is 1" in {
        val event = "00000000011110000001000000000110"
        Event(event).whoScored shouldBe Team2
      }
    }

    "extract the correct value for team 2's points total" in {
      val team2Total = 0
      Event(exampleEventAsBinary1).team2Total shouldBe team2Total
    }

    "extract the correct value for team 1's points total" in {
      val team1Total = 2
      Event(exampleEventAsBinary1).team1Total shouldBe team1Total
    }

    "extract the correct value for elapsed match time" in {
      val secondsOfPlay = 15
      Event(exampleEventAsBinary1).elapsedMatchTime shouldBe secondsOfPlay
    }
  }

  "Events" should {
    val setOfEvents: Events = Events(Set(event1, event2))

    "populate the DataQuery data structure using values from the events" in {
      val lastEventDetails = QueryEvent(event2.whoScored, event2.elapsedMatchTime, event2.pointsScored)

      setOfEvents.queryLastEvent shouldBe lastEventDetails
    }

    "getLastEvent" in {
      setOfEvents.getLastEvent shouldBe event2
    }
  }
}
