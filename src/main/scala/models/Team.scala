package models

sealed trait Team // we could store total points scored in here.
case object Team1 extends Team
case object Team2 extends Team
