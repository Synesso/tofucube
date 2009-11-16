package net.badgerhunt.shares.render

import xml.NodeSeq

object Page {
  val RETURN_TO_PAGE_SESSION_KEY = "return_to_page"
}

abstract class Page {
  val url: String
  def render(): Either[String, NodeSeq]
}