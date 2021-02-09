package validation

import models.{Event, Events, Team1, Team2}

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
  final def compareEvents(prevEvent: Event, newEvent: Event): Event = {
    (newEvent, prevEvent) match {
      case (newEvent, oldEvent) if newEvent.team1Total < oldEvent.team1Total =>
        compareEvents(oldEvent, newEvent.copy(team1Total = oldEvent.team1Total) )

      case (newEvent, oldEvent) if newEvent.team2Total < oldEvent.team2Total =>
        compareEvents(oldEvent, newEvent.copy(team2Total = oldEvent.team2Total))

      case (newEvent, prevEvent) if newEvent.elapsedMatchTime < prevEvent.elapsedMatchTime =>
        compareEvents(prevEvent, newEvent.copy(elapsedMatchTime = prevEvent.elapsedMatchTime))

      case _ => newEvent
    }
  }

  private def handleInconsistentTeamTotals(oldEvent: Event, newEvent: Event): Int = {
    val previousTeam1Total = oldEvent.team1Total
    val previousTeam2Total = oldEvent.team2Total

      newEvent.whoScored match {
      case Team1 => previousTeam1Total + newEvent.pointsScored
      case Team2 => previousTeam2Total + newEvent.pointsScored
    }
  }
}
