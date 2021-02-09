package validation

import models.{Event, Events}
import models.helpers.TestHelper
import ValidateData._

class ValidateDataSpec extends TestHelper {

  "validateEvents" should {
    "correct a list of events with inconsistent data" in {
      val inconsistentList: Events = Events(twoEvents.values.reverse)
      val team1total = event2.team1Total
      val team2total = event2.team2Total
      val elapsedTime = event2.elapsedMatchTime
      val validatedEvent: Event = event1.copy(
        team2Total = team2total,
        team1Total = team1total,
        elapsedMatchTime = elapsedTime)

      val validatedEvents = Events(List(event2, validatedEvent))

      validateEvents(inconsistentList) shouldBe validatedEvents
    }
  }
}
