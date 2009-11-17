package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import model.{Companies, User, Portfolio}
import xml.NodeSeq

abstract class BuyStock(val session: HttpSession) extends Page {
  val user: User
  val portfolio: Portfolio
  val url = "/buy"
  // todo - autocomplete http://digitarald.de/project/autocompleter/1-1/showcase/request-json/
  // todo - calendar picker
  def render: Either[String, NodeSeq] = {
    Right(
      <div>
        <h2>{user} wants to buy something for {portfolio}</h2>
        <form method="post" action="/buying" name="buy" id="buy_form">
          <select name="code">
            {Companies.all.map(c => <option value={c.code}>{c.name} ({c.code})</option>)}
          </select>
          Quantity: <input type="text" name="quantity" value="1"/>
          Price: <input type="text" name="price" value="1.00"/>
          <input type="submit" value="Buy"/>
        </form>
      </div>)
  }

}