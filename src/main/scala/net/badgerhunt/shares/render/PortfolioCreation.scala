package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model.{Portfolio, User}
import xml.NodeSeq
abstract class PortfolioCreation(val session: HttpSession, name: String) extends Page {
  val user: User
  val url = "/creating/portfolio"
  def render(): Either[String, NodeSeq] = {
    val portfolio = Portfolio.create(user, name)
    println("Created %s for %s".format(portfolio, user))
    Left("/portfolio/%s".format(name))
  }
}