package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model.{User, Portfolios}
import scala.{Left => Redirect, Right => Render}
class PortfolioList(val session: HttpSession) extends Page with NeedsUser {
  val url = "/portfolio"
  def xhtml(u: User) = Right(
    <div id="portfolios">
      <div class="heading">Portfolios for {u}</div>
      <div class="icon_list">
        <ul>
          {Portfolios.belongingToUser.map(p => <li>{p.html}</li>)}
          <li><a href="/create/portfolio">Create new portfolio</a></li>
        </ul>
      </div>
    </div>)
}
