package net.badgerhunt.shares.db


import java.text.SimpleDateFormat
import scweery.Connection

object DB {
  val connection = new Connection("jdbc:postgresql://localhost/tofucube", "jem", "password")
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
}