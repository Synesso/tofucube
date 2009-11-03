import sbt._

class StepProject(info: ProjectInfo) extends DefaultWebProject(info) {
  import BasicScalaProject._

  override def useMavenConfigurations = true
  override def packageAction = packageTask(mainClasses +++ mainResources, outputPath, defaultJarName, packageOptions).dependsOn(compile) describedAs PackageDescription

  // for compilation
  val scweery = "net.badgerhunt" % "scweery" % "0.1.2" % "compile->compile"

  // for testing
  val jettytester = "org.mortbay.jetty" % "jetty-servlet-tester" % "7.0.0pre3" % "test->default"
  val scalatest = "org.scala-tools.testing" % "scalatest" % "0.9.5" % "test->default"

  // provided
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided->default"
}
