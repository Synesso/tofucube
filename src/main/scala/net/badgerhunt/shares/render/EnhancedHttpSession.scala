package net.badgerhunt.shares.render

import javax.servlet.http.HttpSession

object EnhancedHttpSession {
  implicit def enhance(session: HttpSession): EnhancedHttpSession = new EnhancedHttpSession(session)
}
class EnhancedHttpSession(session: HttpSession) {
  def get[T](param: String): Option[T] = {
    val storedValue = session.getAttribute(param)
    val generic = storedValue match {
      case null => None
      case _ => Some(storedValue)
    }
    generic.asInstanceOf[Option[T]]
  }
}
