package net.badgerhunt.shares.model

import scweery.Scweery._
import db.DB

case class Company(code: String, name: String, category: String, version: Int)

object Companies {
  lazy val all: List[Company] = infer[List[Company]](DB.connection) { c =>
    c.inferListOf[Company]("select code, version, name, classification from companies") { row =>
      Company(row.string("code"), row.string("name"), row.string("classification"), row.int("version"))
    }
  }
}