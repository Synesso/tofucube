package net.badgerhunt.shares.source


import java.text.SimpleDateFormat
import model.Quote

object QuoteSource {
  val dateFormat = new SimpleDateFormat("\"MM/dd/yyyy\"")
  def current(code: String): Option[Quote] = {
    try {
      val url = "http://download.finance.yahoo.com/d/quotes.csv?s=%s.AX&f=sl1d1t1c1ohgv&e=.csv".format(code)
      val data = io.Source.fromURL(url).mkString.trim.split(",")
      println("quote for %s is %s".format(code, data.mkString(",")))
      Some(Quote(code, dateFormat.parse(data(2)), data(1).toDouble))
    } catch {
      case _ => None
    }
  }
}