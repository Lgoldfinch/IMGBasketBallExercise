package validation

import models.{Event, Events, Team, Team1, Team2}

import scala.annotation.tailrec

object ValidateData {

  def validateEvents(events: Events): Events = {
    val validatedEvents = events.values.foldLeft(List[Event]())((constructedListOfEvents, event) =>
      constructedListOfEvents match {
        case Nil => List(event)
        case ::(head, _) => constructedListOfEvents ++ List(compareEvents(head, event))
        }
      )
    Events(validatedEvents)
  }


  @tailrec
  final def compareEvents(oldEvent: Event, newEvent: Event): Event = {
    (oldEvent, newEvent) match {
      case (prevEvent, nEvent) if nEvent.team1Total < prevEvent.team1Total =>
        val newEvent =  nEvent.copy(team1Total = handleInconsistentTeamTotals(prevEvent, nEvent, Team1))
        compareEvents(prevEvent, newEvent)

      case (prevEvent, nEvent) if nEvent.team2Total < prevEvent.team2Total =>
        val newEvent =  nEvent.copy(team2Total = handleInconsistentTeamTotals(prevEvent, nEvent, Team2))
        compareEvents(oldEvent, newEvent)

      case (prevEvent, nEvent) if nEvent.elapsedMatchTime < prevEvent.elapsedMatchTime =>
        compareEvents(prevEvent, nEvent.copy(elapsedMatchTime = prevEvent.elapsedMatchTime))

      case _ => newEvent
    }
  }

  private def handleInconsistentTeamTotals(oldEvent: Event, newEvent: Event, teamBeingValidated: Team): Int = {
    val previousTeam1Total = oldEvent.team1Total
    val previousTeam2Total = oldEvent.team2Total

    (newEvent.whoScored, teamBeingValidated) match {
      case (Team1, Team1) => previousTeam1Total + newEvent.pointsScored
      case (Team1, Team2) => previousTeam2Total
      case (Team2, Team1) => previousTeam1Total
      case (Team2, Team2) => previousTeam2Total + newEvent.pointsScored
    }
  }
}
