package net.badgerhunt.shares.db


import scweery.Connection

object DB {
  val connection = new Connection("jdbc:postgresql://localhost/tofucube", "jem", "password")
}