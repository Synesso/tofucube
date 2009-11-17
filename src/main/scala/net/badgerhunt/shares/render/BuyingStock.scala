package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import xml.NodeSeq

class BuyingStock(val session: HttpSession, code: String, quantityS: String, priceS: String) extends Page {

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
    Right(<h1>Buying {quantity} of {code} at {price}</h1>)
  }
}