package net.badgerhunt.shares.model

import scweery.Scweery._
import db.DB

object Portfolio {
  def create(user: User, name: String) = {
    use(DB.connection) {
      _.update("insert into portfolios (username, name) values ('%s', '%s')".format(user.name, name))
    }
    Portfolio(name) // todo - scweery should allow create and return structure ... api design needed
  }

}

// for functions that return List[Portfolio]
object Portfolios {
  def belongingTo(user: User): List[Portfolio] = {
    println("Getting portfolios for %s".format(user))
    infer[List[Portfolio]](DB.connection) { conn =>
      conn.inferListOf[Portfolio]("select name from portfolios where username='%s'".format(user.name)) { row =>
        Portfolio(row.string("name"))
      }
    }
  }
}

case class Portfolio(name: String) {
  def html = <a href={"portfolio/%s".format(name.toLowerCase)}>{name}</a>
}