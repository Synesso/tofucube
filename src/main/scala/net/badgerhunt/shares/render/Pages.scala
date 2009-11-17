package net.badgerhunt.shares.render

import com.thinkminimo.step.Step
import source.CompanySource

class Pages extends Step {
  CompanySource.update

  before {
    contentType = "text/html"
  }

  get("/") { Left("/login") }
  get("/login") { new UserLogin().render }
  post("/logging_in") { new UserLoggingIn(session, params("username"), params("password")).render }
  get("/portfolio") { (new PortfolioList(session) with NeedsUser).render }
  get("/portfolio/:name") { (new PortfolioDetail(session, params(":name")) with NeedsUser).render }
  get("/create/portfolio") { (new PortfolioCreate(session) with NeedsUser).render }
  post("/creating/portfolio") { (new PortfolioCreation(session, params("name")) with NeedsUser).render }
  get("/register") { new UserRegister().render }
  post("/creating/user") { new UserCreation(session, params("username"), params("password")).render }
  get("/buy") { (new BuyStock(session) with NeedsUser with NeedsPortfolio).render }
  post("/buying") { (new BuyingStock(session, params("code"), params("quantity"), params("price")) with NeedsUser with NeedsPortfolio).render }
}