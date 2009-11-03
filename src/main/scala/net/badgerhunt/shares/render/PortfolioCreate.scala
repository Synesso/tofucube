package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession
import model.User

class PortfolioCreate(val session: HttpSession) extends Page with NeedsUser {
  val url = "/create/portfolio"
  def xhtml(u: User) = Right(
    <div id="portfolio">
      <div class="heading">Creating a portfolio for {u}</div>
      <form method="post" action="/creating/portfolio" name="portfolio_create" id="portfolio_create_form">
        <p>Name: <input type="text" name="name"></input></p>
        <p><input type="submit" name="Create"></input></p>
      </form>
    </div>)
}