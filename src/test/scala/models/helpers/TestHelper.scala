package models.helpers

import models.Event
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

trait TestHelper extends Matchers with AnyWordSpecLike {
   val exampleEventAsBinary1: String = "00000000011110000001000000000010"
   val exampleEventAsBinary2: String = "00000101101110000001000001000110"

   val event1: Event = Event(exampleEventAsBinary1)
   val event2: Event = Event(exampleEventAsBinary2)
}

