package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import xml.NodeSeq
import EnhancedHttpSession._
import model.Portfolio

trait NeedsPortfolio extends Page {
  val session: HttpSession

  abstract override def render(): Either[String, NodeSeq] =
    if (session.has[Portfolio](Portfolio.SESSION_KEY)) super.render else loginAndReturn(url)

  def loginAndReturn(url: String) = {
    session.setAttribute(Page.RETURN_TO_PAGE_SESSION_KEY, url)
    Left("/portfolio")
  }

  lazy val portfolio = session.get[Portfolio](Portfolio.SESSION_KEY).get
}