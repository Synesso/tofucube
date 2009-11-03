package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import model.User
import EnhancedHttpSession._
import xml.NodeSeq

trait NeedsUser extends Page {
  val session: HttpSession
  def xhtml(u: User): Either[String, NodeSeq]
  def render = session.get[User](User.SESSION_KEY).map(u => xhtml(u)).getOrElse(loginAndReturn(url))
  def loginAndReturn(url: String) = {
    session.setAttribute(Page.RETURN_TO_PAGE_SESSION_KEY, url)
    Left("/login")
  }
}