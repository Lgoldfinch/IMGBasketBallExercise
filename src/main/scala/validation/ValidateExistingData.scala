package validation

import models.{Event, Events}

import scala.annotation.tailrec

case object ValidateExistingData {

  @tailrec
  final def compareEvents(oldEvent: Event, newEvent: Event): Event = {
//    val oldEvent = existingEvents.getLastEvent

    (newEvent, oldEvent) match {
      case (newEvent, oldEvent) if newEvent.team1Total < oldEvent.team1Total =>
        compareEvents(newEvent.copy(team1Total = oldEvent.team1Total),  oldEvent )

      case (newEvent, oldEvent) if newEvent.team2Total < oldEvent.team2Total =>
        compareEvents(newEvent.copy(team2Total = oldEvent.team2Total), oldEvent)

      case (newEvent, oldEvent) if newEvent.elapsedMatchTime < oldEvent.elapsedMatchTime =>
        compareEvents(newEvent.copy(elapsedMatchTime = oldEvent.elapsedMatchTime), oldEvent)

      case _ => newEvent
    }
  }


}
