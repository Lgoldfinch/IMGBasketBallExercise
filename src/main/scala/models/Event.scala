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

  /**
   * This might not have been necessary but I assumed this was what the first page of the test specification intended
   * when it asked for the resulting match score.
   */

  def getMatchScore: String = {
    val team1Score = getLastEvent.team1Total
    val team2Score = getLastEvent.team2Total

    def whichTeamLeads(teamNumber: String) = s"Team $teamNumber leads, "

    if (team1Score > team2Score) whichTeamLeads("1") + team1Score + "-" + team2Score
    else if (team1Score < team2Score) whichTeamLeads("2") + team2Score + "-" + team1Score
    else team1Score + "-" + team2Score
  }
}