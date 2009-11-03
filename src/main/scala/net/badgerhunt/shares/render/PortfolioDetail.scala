package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model.User

class PortfolioDetail(val session: HttpSession, name: String) extends Page with NeedsUser {
  val url = "/portfolio/%s".format(name)
  def xhtml(u: User) = Right(
    <div id="portfolio">
      <div class="heading">{u}'s Portfolio: {name}</div>
    </div>)
}
