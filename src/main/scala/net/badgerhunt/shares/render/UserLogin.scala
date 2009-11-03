package net.badgerhunt.shares.render

class UserLogin extends Page {
  val url = "login"
  def render = Right(
    <form method="post" action="/logging_in" name="login" id="login_form">
      <p>Username: <input type="text" name="username"></input></p>
      <p>Password: <input type="password" name="password"></input></p>
      <p><input type="submit" name="Login"></input></p>
    </form>)
}