package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model.{User, Portfolios}
import scala.{Left => Redirect, Right => Render}
import xml.NodeSeq

abstract class PortfolioList(val session: HttpSession) extends Page {
  val user: User
  val url = "/portfolio"
  def render(): Either[String, NodeSeq] = Right(
    <div id="portfolios">
      <div class="heading">Portfolios for {user}</div>
      <div class="icon_list">
        <ul>
          {Portfolios.belongingTo(user).map(p => <li>{p.html}</li>)}
          <li><a href="/create/portfolio">Create new portfolio</a></li>
        </ul>
      </div>
    </div>)
}
