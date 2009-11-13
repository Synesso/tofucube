package net.badgerhunt.shares.model


import cipher.MD5
import db.DB
import scweery.Scweery._

object User {
  val SESSION_KEY = "logged_in_user"
  def withUsername(name: String) = new User(name) // todo - from DB
  def create(name: String, password: String) = {
    use(DB.connection) {
      _.update("insert into users (name, hash) values ('%s', '%s')".format(name, MD5.sum("%s::%s".format(name, password))))
    }
    new User(name) // todo - Option to represent failure to create.
  }
}

case class User(name: String) {
  def authenticatesWith(passwordHash: String) = if (name == "jem") passwordHash == "4a0ead46f94d0fff47d2fd646627d125" else true
}