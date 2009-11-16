package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import xml.NodeSeq
import EnhancedHttpSession._
import model.Portfolio

trait NeedsPortfolio extends Page {
  val session: HttpSession

  abstract override def render(): Either[String, NodeSeq] =
    if (session.has[Portfolio](Portfolio.SESSION_KEY)) super.render else Left(PortfolioList.url)

  lazy val portfolio = session.get[Portfolio](Portfolio.SESSION_KEY).get
}