import sbt._
import Keys._
import sbt.Defaults._

object Build extends Build {
  /** ScalaMeter testing framework. This value can be used in the build.sbt file.
    */
  val scalaMeterFramework = new TestFramework("org.scalameter.ScalaMeterFramework")

  private val extraSettings = Seq()

  // dummy project with default settings + extraSettings.
  // This has the same effect as putting extraSettings in build.sbt.
  lazy val root = Project (
    id = "scalameter-tutorial",
    base = file("."),
    settings = defaultSettings ++ extraSettings
  )
}
