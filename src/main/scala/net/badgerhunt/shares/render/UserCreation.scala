package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession
import model.User

class UserCreation(session: => HttpSession, name: String, password: String) extends Page {
  val url = "/creating/user"
  def render = {
    if (User.exists(name)) {
      Left("/register")
    } else {
      val user = User.create(name, password)
      session.setAttribute(User.SESSION_KEY, user)
      Left("/portfolio")
    }
  }
}