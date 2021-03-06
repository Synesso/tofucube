package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import model.User
import EnhancedHttpSession._
import xml.NodeSeq

trait NeedsUser extends Page {
  val session: HttpSession

  abstract override def render(): Either[String, NodeSeq] =
    if (session.has[User](User.SESSION_KEY)) super.render else loginAndReturn(url)

  def loginAndReturn(url: String) = {
    session.setAttribute(Page.RETURN_TO_PAGE_SESSION_KEY, url)
    Left("/login")
  }

  lazy val user = session.get[User](User.SESSION_KEY).get
}