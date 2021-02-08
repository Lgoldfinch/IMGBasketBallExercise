package models

case class QueryEvent(
                    lastTeamToScore: Team,
                    whatPointInMatch: Int,
                    resultingScore: Int
                    )

