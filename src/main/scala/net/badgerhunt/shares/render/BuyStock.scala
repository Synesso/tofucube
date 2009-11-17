package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import model.{Companies, User, Portfolio}
import xml.NodeSeq

abstract class BuyStock(val session: HttpSession) extends Page {
  val user: User
  val portfolio: Portfolio
  val url = "/buy"
  def render: Either[String, NodeSeq] = {
    Right(
      <div>
        <h2>{user} wants to buy something for {portfolio}</h2>
        <ol>
        {Companies.all.map(c => <li>{c.name} ({c.code})</li>)}
        </ol>
      </div>)
  }

}