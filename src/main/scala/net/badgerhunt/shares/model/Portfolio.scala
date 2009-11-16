package net.badgerhunt.shares.model

import scweery.Scweery._
import db.DB

object Portfolio {
  val SESSION_KEY = "selected_portfolio"

  def create(user: User, name: String) = {
    use(DB.connection) {
      _.update("insert into portfolios (username, name) values ('%s', '%s')".format(user.name, name))
    }
    Portfolio(name) // todo - scweery should allow create and return structure ... api design needed
  }

  def namedForUser(user: User, name: String): Option[Portfolio] = {
    infer[Option[Portfolio]](DB.connection) {
      _.inferListOf[Portfolio]("select name from portfolios where username='%s' and name='%s'".format(user.name, name)) { row =>
        Portfolio(row.string("name"))
      } match {
        case List(p) => Some(p)
        case _ => None
      }
    }
  }

}

// for functions that return List[Portfolio]
object Portfolios {
  def belongingTo(user: User): List[Portfolio] = {
    println("Getting portfolios for %s".format(user))
    infer[List[Portfolio]](DB.connection) {
      _.inferListOf[Portfolio]("select name from portfolios where username='%s'".format(user.name)) { row =>
        Portfolio(row.string("name"))
      }
    }
  }
}

case class Portfolio(name: String) {
  def html = <a href={"portfolio/%s".format(name.toLowerCase)}>{name}</a>
}