package models

import scala.annotation.tailrec

case class Event(binaryEvent: String) {
  private val lengthOfEvent = binaryEvent.length
  private val indexOfPointsScored = 30
  private val indexOfWhoScored = 29
  private val indexOfTeam2Total = 21
  private val indexOfTeam1Total = 13

  // extract this into ParseEvent so that we just have params in here (or something)
  def convertBinaryIntoEvent(startIndex: Int, endIndex: Int): Int =
    Integer.parseInt(binaryEvent.substring(startIndex, endIndex), 2)

  val pointsScored: Int = convertBinaryIntoEvent(indexOfPointsScored, lengthOfEvent)

  val whoScored: Team = convertBinaryIntoEvent(indexOfWhoScored, indexOfPointsScored) match {
    case 0 => Team1
    case 1 => Team2
  }

  val team2Total: Int = convertBinaryIntoEvent(indexOfTeam2Total, indexOfWhoScored)

  val team1Total: Int = convertBinaryIntoEvent(indexOfTeam1Total, indexOfTeam2Total)

  val elapsedMatchTime: Int = convertBinaryIntoEvent(0, indexOfTeam1Total)
}

case class Events(values: Set[Event]) {
  val getLastEvent: Event = values.last

  val queryLastEvent: QueryEvent = QueryEvent(
    lastTeamToScore = getLastEvent.whoScored,
    whatPointInMatch = getLastEvent.elapsedMatchTime,
    resultingScore = getLastEvent.pointsScored
  )


  def getNumberOfEventsFromRight(numberOfEvents: Int): Set[Event] = {
    val lengthOfEvents = numberOfEvents -
    @tailrec
    def go(currentList: List[Event] = List(), remainingList: List[Event] = reversedSet, n: Int = numberOfEvents): Set[Event] = {
      if(n == 0) {
        currentList.toSet
      } else {
        reversedSet match {
          case List() => currentList.toSet
          case head :: tail => go(currentList ++ head)
        }
      }
      }
    }
}