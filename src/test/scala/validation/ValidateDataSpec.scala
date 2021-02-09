package validation

import models.helpers.TestHelper
import models.{Event, Events}
import validation.ValidateData._

class ValidateDataSpec extends TestHelper {

  "validateEvents" should {
    "correct a list of events with inconsistent data" in {
      val inconsistentList: Events = Events(twoEvents.values.reverse)
      val pointScoredInLatestEvent = 2
      val team1total = event2.team1Total
      val team2total = event2.team2Total
      val elapsedTime = event2.elapsedMatchTime
      val validatedEvent: Event = event1.copy(
        team2Total = team2total,
        team1Total = team1total + pointScoredInLatestEvent,
        elapsedMatchTime = elapsedTime)

      val validatedEvents = Events(List(event2, validatedEvent))

      validateAndCleanEvents(inconsistentList) shouldBe validatedEvents
    }
  }
}