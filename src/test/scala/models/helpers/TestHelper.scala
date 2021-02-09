package models.helpers

import models.ParseEvent.feedData
import models.{Event, Events, ParseEvent}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

trait TestHelper extends Matchers with AnyWordSpecLike {
   val exampleHexValue1: Int = 0x781002
   val exampleHexValue2: Int = 0x1310c8a1
   val event1: Event = ParseEvent.convertToEvent(exampleHexValue1)
   val event2: Event = ParseEvent.convertToEvent(exampleHexValue2)
   val twoEvents: Events = Events(List(event1, event2))

   val exampleDataFromTestSpec: List[Int] = List(exampleHexValue1, 0xf0101f, exampleHexValue2, 0x29f981a2, 0x48332327)
   val eventsCreatedByFeedData: Events = feedData(exampleDataFromTestSpec)


}

