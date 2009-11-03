package net.badgerhunt.shares.model


object User {
  val SESSION_KEY = "logged_in_user"
  def withUsername(username: String) = new User(1, username)
}

case class User(id: Long, username: String) {
  def authenticatesWith(passwordHash: String) = if (username == "jem") passwordHash == "4a0ead46f94d0fff47d2fd646627d125" else true
}