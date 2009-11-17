package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import model.{User, Portfolio}
import xml.NodeSeq

abstract class ChartStock(val session: HttpSession, code: String) extends Page {
  val url = "/chart/%s".format(code)
  val user: User
  val portfolio: Portfolio

  def render: Either[String, NodeSeq] = {
    Right(<h1>Charting {code.toUpperCase} for {user} within {portfolio}</h1>)
  }
}