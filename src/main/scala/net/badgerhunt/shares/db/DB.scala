package net.badgerhunt.shares.db


import scweery.Connection

object DB {
  val connection = new Connection("jdbc:vendorx:localhost", "synesso", "e1337^hacksaw")
}