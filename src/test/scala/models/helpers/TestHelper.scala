package models.helpers

import models.{Event, ParseEvent}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

trait TestHelper extends Matchers with AnyWordSpecLike {
   val exampleHexValue1: Int = 0x781002
   val exampleHexValue2: Int = 0x1310c8a1
   val event1: Event = ParseEvent.convertToEvent(exampleHexValue1)
   val event2: Event = ParseEvent.convertToEvent(exampleHexValue2)
}

