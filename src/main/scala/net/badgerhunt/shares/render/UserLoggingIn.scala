package net.badgerhunt.shares.render


import cipher.MD5
import javax.servlet.http.HttpSession
import model.User
import EnhancedHttpSession._

class UserLoggingIn(session: => HttpSession, username: String, password: String) extends Page {
  val url ="/logging_in"
  def render = {
    val user = User.withAuthentication(username, password)
    user.map(u => session.setAttribute(User.SESSION_KEY, u)) // side-effect
    def returnPage: String = {
      val forwardTo = session.get[String]("return_to_page").getOrElse("/portfolio")
      session.removeAttribute("return_to_page")
      forwardTo
    }
    Left(user.map(u => returnPage).getOrElse("/login"))
  }
}