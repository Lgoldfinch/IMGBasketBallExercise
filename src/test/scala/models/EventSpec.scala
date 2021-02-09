package models

import models.helpers.TestHelper

class EventSpec extends TestHelper {

  "Events" should {
    val setOfEvents: Events = Events(List(event1, event2))

    "populate the DataQuery data structure using values from the events" in {
      val lastEventDetails = QueryEvent(event2.whoScored, event2.elapsedMatchTime, event2.pointsScored)

      setOfEvents.queryLastEvent shouldBe lastEventDetails
    }

    "getLastEvent" in {
      setOfEvents.getLastEvent shouldBe event2
    }
  }
}
