package net.badgerhunt.shares.render


import javax.servlet.http.HttpSession

class UserRegister extends Page {
  val url = "/register"
  def render = Right(
    <form method="post" action="/creating/user" name="register" id="register_form">
      <p>Username: <input type="text" name="username"></input></p>
      <p>Password: <input type="password" name="password"></input></p>
      <p><input type="submit" name="Login"></input></p>
    </form>
  )
}