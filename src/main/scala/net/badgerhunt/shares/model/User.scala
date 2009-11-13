package net.badgerhunt.shares.model


import cipher.MD5
import db.DB
import scweery.Scweery._

object User {
  val SESSION_KEY = "logged_in_user"

  def withAuthentication(name: String, password: String): Option[User] = infer[Option[User]](DB.connection) { conn =>
    val query = "select name from users where name='%s' and hash='%s'".format(name, MD5 sum "%s::%s".format(name, password))
    println(query)
    val users = conn.inferListOf[User](query) { row => // todo - scweery should debug the queries 
      User(row.string(0))
    }// todo - scweery should have an infer[T] that returns Either[Int, T] (where Int is a counter of results when not == 1)
    users match {
      case Nil => None
      case _ => Some(users(0))
    }
  }

  def exists(name: String) = infer[Boolean](DB.connection) { conn =>
    conn.inferListOf[User]("select * from users where name='%s'".format(name)) { row =>
      User(row.string(0))
    }.size > 0
  }

  def create(name: String, password: String) = {
    use(DB.connection) {
      _.update("insert into users (name, hash) values ('%s', '%s')".format(name, MD5.sum("%s::%s".format(name, password))))
    }
    new User(name) // todo - Option to represent failure to create.
  }
}

case class User(name: String)