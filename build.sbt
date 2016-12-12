import ReleaseTransformations._

name := "sbt-sonar"
organization := "com.github.mwz"
homepage := Some(url("https://github.com/mwz/sbt-sonar"))
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

sbtPlugin := true
publishMavenStyle := false
scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked",
  "-deprecation"
)

// Bintray
bintrayRepository := "sbt-plugin-releases"
bintrayPackage := "sbt-sonar"
bintrayReleaseOnPublish := false

// Release
releaseVersionBump := sbtrelease.Version.Bump.Minor
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepTask(bintrayRelease in thisProjectRef.value),
  setNextVersion,
  commitNextVersion,
  pushChanges
)