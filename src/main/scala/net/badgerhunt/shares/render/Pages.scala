package net.badgerhunt.shares.render

import com.thinkminimo.step.Step

class Pages extends Step {
  before {
    contentType = "text/html"
  }

  get("/") { Left("/login") }
  get("/login") { new UserLogin().render }
  post("/logging_in") { new UserLoggingIn(session, params("username"), params("password")).render }
  get("/portfolio") { new PortfolioList(session).render }
  get("/portfolio/:name") { new PortfolioDetail(session, params(":name")).render }
  get("/create/portfolio") { new PortfolioCreate(session).render }
  post("/creating/portfolio") { new PortfolioCreation(session, params("name")).render }
}