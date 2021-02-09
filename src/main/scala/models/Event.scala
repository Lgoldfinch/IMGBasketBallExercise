package models


case class Event(
                  pointsScored: Int,
                  whoScored: Team,
                  team2Total: Int,
                  team1Total: Int,
                  elapsedMatchTime: Int
                )

case class Events(values: List[Event]) {
  val getLastEvent: Event = values.last

  val queryLastEvent: QueryEvent = QueryEvent(
    lastTeamToScore = getLastEvent.whoScored,
    whatPointInMatch = getLastEvent.elapsedMatchTime,
    resultingScore = getLastEvent.pointsScored
  )
}