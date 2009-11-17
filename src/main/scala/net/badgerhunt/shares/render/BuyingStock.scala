package net.badgerhunt.shares.render


import db.DB
import javax.servlet.http.HttpSession
import model.{User, Portfolio}
import xml.NodeSeq
import scweery.Scweery._

abstract class BuyingStock(val session: HttpSession, code: String, quantityS: String, priceS: String) extends Page {

  val user: User
  val portfolio: Portfolio
  val url = "/buying"

  def asDouble(s: String, valid: Double => Boolean): Option[Double] = try {
    val d = s.toDouble
    if (valid(d)) Some(d) else None
  } catch {
    case _ => None
  }

  def asInt(s: String, valid: Int => Boolean): Option[Int] = try {
    val i = s.toInt
    if (valid(i)) Some(i) else None
  } catch {
    case _ => None
  }

  val price = asDouble(priceS, _ > 0.0)
  val quantity = asInt(quantityS, _ > 0)

  def render: Either[String, NodeSeq] = {
    if (price.isDefined && quantity.isDefined) {
      use(DB.connection) { c=>
        val sql = "insert into buys (portfolio_id, company, quantity, price) values (%s, '%s', %s, %s)".format(portfolio.id, code, quantity.get, price.get)
        println(sql)
        c.update(sql)
      }
      Right(<h1>Bought {quantity} of {code} at {price}</h1>)
    } else {
      Left("/buy")
    }
  }
}