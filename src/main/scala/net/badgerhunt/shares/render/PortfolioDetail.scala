package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model._

class PortfolioDetail(val session: HttpSession, name: String) extends Page with NeedsUser {
  val url = "/portfolio/%s".format(name)
  def xhtml(u: User) = {
    Portfolio.namedForUser(u, name).map(p =>
      Right(
        <div id="portfolio">
          <div class="heading">{u.name}'s Portfolio: {p.name}</div>
        </div>)
    ).getOrElse(Left("/portfolio"))
  }
}
