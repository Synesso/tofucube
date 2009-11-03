package net.badgerhunt.shares.model

import scweery.Scweery._
import db.DB

object Portfolio {
  def create(user: User, name: String) = use(DB.connection) {
    _.update("insert into portfolio (user_id, name) values (%s, '%s')".format(user.id, name))
  }
}

// for functions that return List[Portfolio]
object Portfolios {
  // todo - implement user and replace this
  def belongingToUser: List[Portfolio] = Portfolio("Jem") :: Nil
  def all = belongingToUser
}

case class Portfolio(name: String) {
  def html = <a href={"portfolio/%s".format(name.toLowerCase)}>{name}</a>
}