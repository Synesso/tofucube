package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model.User
import xml.NodeSeq

abstract class PortfolioCreate(val session: HttpSession) extends Page {
  val user: User
  val url = "/create/portfolio"
  def render(): Either[String, NodeSeq] = Right(
    <div id="portfolio">
      <div class="heading">Creating a portfolio for {user}</div>
      <form method="post" action="/creating/portfolio" name="portfolio_create" id="portfolio_create_form">
        <p>Name: <input type="text" name="name"></input></p>
        <p><input type="submit" name="Create"></input></p>
      </form>
    </div>)
}