package validation

import models.{Event, Events}
import play.api.Logger

object ValidateExistingData {
 // get last known value

  def compareEvents(newEvent: Event, existingEvents: Events): Event = {
    val oldEvent = existingEvents.getLastEvent
    (newEvent, oldEvent) match {
      case (newEvent, oldEvent) if newEvent.team1Total < oldEvent.team1Total => {

        compareEvents(newEvent.copy(team))
      }
    }
  }

}
