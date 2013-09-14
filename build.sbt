scalaVersion := "2.10.2"

scalacOptions := Seq("-deprecation", "-feature", "-Xlint")

// SCALA METER BEGINS

// resolver for ScalaMeter
resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"

// ScalaMeter 0.3
libraryDependencies += "com.github.axel22" %% "scalameter" % "0.3"

logBuffered := false

testFrameworks += scalaMeterFramework

// Allow ScalaMeter to run on JDK 6 ─ see
// http://axel22.github.io/scalameter/2013/02/14/release_0_3.html
testOptions += Tests.Argument(scalaMeterFramework, "-preJDK7")
