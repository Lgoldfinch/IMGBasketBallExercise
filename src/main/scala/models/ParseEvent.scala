package models

object ParseEvent { // case class
  def convertToEvent(hexValue: Int): Event = {
    val expectedLengthOfEvent = 32
    val eventAsBinary: String = hexValue.toBinaryString
    val lengthDifference: Int = expectedLengthOfEvent - eventAsBinary.length

    if (lengthDifference == 0) {
      convertBinaryIntoEvent(eventAsBinary)
    } else {
      val eventWithPrecedingZeroes = ("0" * lengthDifference) ++ eventAsBinary

      convertBinaryIntoEvent(eventWithPrecedingZeroes)
    }
  }

  def convertBinaryIntoEvent(binaryEvent: String): Event = {
    val pointsScored: Int = convertBinaryIntoEvent(binaryEvent, indexOfPointsScored, lengthOfEvent)
    val whoScored: Team = convertBinaryIntoEvent(binaryEvent, indexOfWhoScored, indexOfPointsScored) match {
      case 0 => Team1
      case 1 => Team2
    }
    val team2Total: Int = convertBinaryIntoEvent(binaryEvent, indexOfTeam2Total, indexOfWhoScored)
    val team1Total: Int = convertBinaryIntoEvent(binaryEvent, indexOfTeam1Total, indexOfTeam2Total)
    val elapsedMatchTime: Int = convertBinaryIntoEvent(binaryEvent, 0, indexOfTeam1Total)

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

  def feedData(rawEvents: Set[Int]): Events = {
    val convertedEvents: Set[Event] = rawEvents.map(
      hexEvent => {
        convertToEvent(hexEvent)
      })

    Events(convertedEvents)
  }

  private val lengthOfEvent = 32
  private val indexOfPointsScored = 30
  private val indexOfWhoScored = 29
  private val indexOfTeam2Total = 21
  private val indexOfTeam1Total = 13
}



