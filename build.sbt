import Settings._
import ModuleSettings._
import Dependencies._

lazy val graphql = project
  .in(file("graphql"))
  .minimalSettings
  .dependsOn(ultraventaDomain, ultraventaApi, ultraventaInfra)
  .settings(
    libraryDependencies := caliban ++ zioHttp
  )

lazy val ultraventaDomain = domainProject("ultraventa").minimalSettings
  .settings(
    libraryDependencies := circe
  )

lazy val ultraventaApi = apiProject("ultraventa").minimalSettings
  .dependsOn(ultraventaDomain % "compile->compile;test->test")
  .settings(
    libraryDependencies := zio ++ zioConfig ++ zioLogging
  )

lazy val ultraventaInfra = infraProject("ultraventa").minimalSettings
  .dependsOn(ultraventaDomain % "compile->compile;test->test")
  .dependsOn(ultraventaApi % "compile->compile;test->test")
  .settings(
    libraryDependencies := zioTest,
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

lazy val root = (project in file(".")).minimalSettings
  .aggregate(
    ultraventaDomain,
    ultraventaApi,
    ultraventaInfra,
    graphql
  )
  .dependsOn(ultraventaApi, ultraventaDomain, ultraventaInfra, graphql)
  .settings(
    name := "uzcript-backend",
    version := "0.0.1-SNAPSHOT",
    Compile / run / mainClass := Some("com.ernestochero.Main")
  )

addCommandAlias("fix", "; scalafix; Test / scalafix")
addCommandAlias("fmt", "; scalafmt; scalafmtSbt; Test / scalafmt")
addCommandAlias("fixCheck", "; scalafix --check; Test / scalafix --check")
addCommandAlias("fmtCheck", "; scalafmtCheck; scalafmtSbtCheck; Test / scalafmtCheck")
