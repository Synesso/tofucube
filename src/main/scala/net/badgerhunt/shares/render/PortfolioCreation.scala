package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model.{Portfolio, User}
class PortfolioCreation(val session: HttpSession, name: String) extends Page with NeedsUser {
  val url = "/creating/portfolio"
  def xhtml(u: User) = {
    val portfolio = Portfolio(name)
    println("Created %s for %s".format(portfolio, u))
    Left("/portfolio/%s".format(name))
  }
}