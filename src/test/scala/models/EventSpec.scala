package models

import models.helpers.TestHelper

class EventSpec extends TestHelper {

  "Events" should {
    "return the match score when Team 1's total is higher than team Team 2's" in {
      val team1Score = 25
      val team2Score = 20
      event2.team1Total shouldBe team1Score
      event2.team2Total shouldBe team2Score

      events.getMatchScore shouldBe s"Team 1 leads, $team1Score-$team2Score"
     }

    "getLastEvent" in {
      events.getLastEvent shouldBe event2
    }
  }
}
