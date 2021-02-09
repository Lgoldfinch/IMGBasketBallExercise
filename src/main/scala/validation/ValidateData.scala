package validation

import models._

import scala.annotation.tailrec

object ValidateData {

  /**
   * this event: Event(3,Team1,234,232,1500) in sample 2 means that my code doesn't correctly update.
   * Ideally, I'd identify obvious outliers early on and then append them to the end of the list.
   * I ran out of time to implement this.
   */

  def validateAndCleanEvents(events: Events): Events = {
    val validatedEvents = events.values.foldLeft(List[Event]())((acc, event) =>
      acc match {
        case Nil => List(event)
        case ::(head, _) => {
          val newEvent: Event = compareEvents(head, event)
          val result: List[Event] = List(newEvent) ++ acc
          result
          }
        }
      )
    Events(validatedEvents.reverse)
  }

  @tailrec
  final def compareEvents(oldEvent: Event, newEvent: Event): Event = {
    (oldEvent, newEvent) match {

      case (prevEvent, nEvent) if nEvent.team1Total < prevEvent.team1Total =>
        val newEvent =  nEvent.copy(team1Total = handleInconsistentTeamTotals(prevEvent, nEvent, Team1))
        compareEvents(prevEvent, newEvent)

      case (prevEvent, nEvent) if nEvent.team2Total < prevEvent.team2Total =>
        val newEvent =  nEvent.copy(team2Total = handleInconsistentTeamTotals(prevEvent, nEvent, Team2))
        compareEvents(prevEvent, newEvent)

      case (prevEvent, nEvent) if nEvent.elapsedMatchTime < prevEvent.elapsedMatchTime =>
        compareEvents(prevEvent, nEvent.copy(elapsedMatchTime = prevEvent.elapsedMatchTime))

      case _ => newEvent
    }
  }

   def handleInconsistentTeamTotals(oldEvent: Event, newEvent: Event, teamBeingValidated: Team): Int = {
    val previousTeam1Total = oldEvent.team1Total
    val previousTeam2Total = oldEvent.team2Total
    val newEventScore = newEvent.pointsScored

    (newEvent.whoScored, teamBeingValidated) match {
      case (Team1, Team1) => previousTeam1Total + newEventScore
      case (Team1, Team2) => previousTeam2Total
      case (Team2, Team1) => previousTeam1Total
      case (Team2, Team2) => previousTeam2Total + newEventScore
    }
  }
}
