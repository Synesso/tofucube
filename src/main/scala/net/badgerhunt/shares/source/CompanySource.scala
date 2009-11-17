package net.badgerhunt.shares.source


import db.DB
import io.Source
import scweery.Scweery._

object CompanySource {
  def update = {
    def escape(s: String) = s.replaceAll("'", "\\\\'") // todo - scweery should have an escape function that can do this, postgres specific?
    val companyData = Source.fromURL("http://www.asx.com.au/asx/research/ASXListedCompanies.csv").getLines.drop(3).map(_.split("\"")).map(c => (c(1).trim, c(2).substring(1, 4), c(3).trim))
    val withValidCategory = companyData.filter(!_._3.equals("GICS Sector Code Not Applicable")).filter(!_._3.equals("Classification Pending"))
    val capitalised = withValidCategory.map(t => (t._1.split(" ").map(w => if (w.equals(t._2)) w else w.toLowerCase).map(w => if (w.equals("and") || w.equals("of")) w else w.capitalize).mkString(" "), t._2, t._3))
    use(DB.connection) { c =>
      c.update("truncate table companies")
      capitalised.foreach{company =>
        val sql = "insert into companies (code, version, name, classification) values ('%s', 1, E'%s', E'%s')".format(company._2, escape(company._1), escape(company._3))
        println(sql)
        c.update(sql)
      }
    }
  }
}