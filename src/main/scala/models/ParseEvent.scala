package models

object ParseEvent {

  private val lengthOfEvent = binaryEvent.length
  private val indexOfPointsScored = 30
  private val indexOfWhoScored = 29
  private val indexOfTeam2Total = 21
  private val indexOfTeam1Total = 13

  def convertToEvent(hexValue: Int): Event = {
    val expectedLengthOfEvent = 32
    val eventAsBinary: String = hexValue.toBinaryString
    val lengthDifference: Int = expectedLengthOfEvent - eventAsBinary.length

    if (lengthDifference == 0) {
      Event(eventAsBinary)
    } else {
      val eventWithPrecedingZeroes = ("0" * lengthDifference) ++ eventAsBinary

      Event(eventWithPrecedingZeroes)
    }
  }

  def convertBinaryIntoEvent(eventInBinary: String, startIndex: Int, endIndex: Int): Event = {
    def convert = Integer.parseInt(eventInBinary.substring(startIndex, endIndex), 2)
    Event(
      convert
    )
  }


  def feedData(rawEvents: Set[Int]): Events = {
    val convertedEvents: Set[Event] = rawEvents.map(
      hexEvent => {
        convertToEvent(hexEvent)
      })

      Events(convertedEvents)
  }
}



