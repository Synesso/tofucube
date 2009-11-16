package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model._
import xml.NodeSeq

abstract class PortfolioDetail(val session: HttpSession, name: String) extends Page {
  val user: User
  val url = "/portfolio/%s".format(name)
  def render(): Either[String, NodeSeq] = {
    Portfolio.namedForUser(user, name).map{p =>
      session.setAttribute(Portfolio.SESSION_KEY, p)
      Right(
        <div id="portfolio">
          <div class="heading">{user.name}'s Portfolio: {p.name}</div>
          <a href="/buy">buy</a>
        </div>)
    }.getOrElse(Left("/portfolio"))
  }
}
