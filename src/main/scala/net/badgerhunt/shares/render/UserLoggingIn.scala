package net.badgerhunt.shares.render


import cipher.MD5
import javax.servlet.http.HttpSession
import model.User
import EnhancedHttpSession._

class UserLoggingIn(session: => HttpSession, username: String, password: String) extends Page {
  val url ="/logging_in"
  def render = {
    val user = User withUsername username
    val hash = MD5 sum "%s::%s".format(username, password)
    val authenticatedUser = if (user authenticatesWith hash) Some(user) else None
    authenticatedUser.map(u => session.setAttribute(User.SESSION_KEY, u)) // side-effect
    def returnPage: String = {
      val forwardTo = session.get[String]("return_to_page").getOrElse("/portfolio")
      session.removeAttribute("return_to_page")
      forwardTo
    }
    Left(authenticatedUser.map(u => returnPage).getOrElse("/login"))
  }
}