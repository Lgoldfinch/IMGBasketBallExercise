package models


object ParseEvent {

  def convertToEvent(hexValue: Int): Event = {
    val eventAsBinary: String = hexValue.toBinaryString
    val lengthDifference: Int = lengthOfEvent - eventAsBinary.length

    if (lengthDifference == 0) {
      buildEvent(eventAsBinary)
    } else {
      val eventWithPrecedingZeroes = ("0" * lengthDifference) ++ eventAsBinary

      buildEvent(eventWithPrecedingZeroes)
    }
  }

  def buildEvent(eventAsBinaryString: String): Event = {
    val pointsScored: Int = convertBinaryIntoEvent(eventAsBinaryString, indexOfPointsScored, lengthOfEvent)
    val team2Total: Int = convertBinaryIntoEvent(eventAsBinaryString, indexOfTeam2Total, indexOfWhoScored)
    val team1Total: Int = convertBinaryIntoEvent(eventAsBinaryString, indexOfTeam1Total, indexOfTeam2Total)
    val elapsedMatchTime: Int = convertBinaryIntoEvent(eventAsBinaryString, 0, indexOfTeam1Total)
    val whoScored: Team = convertBinaryIntoEvent(eventAsBinaryString, indexOfWhoScored, indexOfPointsScored) match {
      case 0 => Team1
      case 1 => Team2
    }

    Event(
      pointsScored,
      whoScored,
      team2Total,
      team1Total,
      elapsedMatchTime
    )
  }

  def convertBinaryIntoEvent(eventAsBinaryString: String, startIndex: Int, endIndex: Int): Int =
    Integer.parseInt(eventAsBinaryString.substring(startIndex, endIndex), 2)

  def feedData(rawEvents: List[Int]): Events = {
    val convertedEvents: List[Event] = rawEvents.map(
      hexEvent => {
        convertToEvent(hexEvent)
      })

    Events(convertedEvents)
//    convertedEvents.foldLeft(convertedEvents)((a, event) => ValidateExistingData.compareEvents(a, event))
  }

  private val lengthOfEvent = 32
  private val indexOfPointsScored = 30
  private val indexOfWhoScored = 29
  private val indexOfTeam2Total = 21
  private val indexOfTeam1Total = 13
}



