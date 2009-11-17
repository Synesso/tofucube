package net.badgerhunt.shares.model

import scweery.Scweery._
import db.DB

object Portfolio {
  val SESSION_KEY = "selected_portfolio"

  def create(user: User, name: String) = {
    use(DB.connection) {
      _.update("insert into portfolios (username, name) values ('%s', '%s')".format(user.name, name))
    }
    val id = infer[Int](DB.connection) { c=>
      c.inferListOf[Int]("select max(id) from portfolios where username='%s' and name='%s'".format(user.name, name))(_.int(0))(0)
    }
    Portfolio(id, name) // todo - scweery should allow create and return structure ... api design needed
  }

  def namedForUser(user: User, name: String): Option[Portfolio] = {
    infer[Option[Portfolio]](DB.connection) {
      _.inferListOf[Portfolio]("select id, name from portfolios where username='%s' and name='%s'".format(user.name, name)) { row =>
        Portfolio(row.int("id"), row.string("name"))
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
      _.inferListOf[Portfolio]("select id, name from portfolios where username='%s'".format(user.name)) { row =>
        Portfolio(row.int("id"), row.string("name"))
      }
    }
  }
}

case class Portfolio(id: Int, name: String) {
  def html = <a href={"portfolio/%s".format(name.toLowerCase)}>{name}</a>
}