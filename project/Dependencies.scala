import sbt._
object Versions {
  val scala2 = "2.13.8"
  val zio = "2.0.2"
  val zioConfig = "3.0.2"
  val zioLogging = "2.1.1"
  val zioPrelude = "1.0.0-RC14"
  val scalatest = "3.2.12"
  val logbackClassic = "1.4.1"
  val circe = "0.14.3"
  val scalafixOrganizeImports = "0.6.0"
  val caliban = "2.0.1"
  val zioHttp = "2.0.0-RC9"
}

object Dependencies {
  lazy val zio = Seq(
    "dev.zio" %% "zio" % Versions.zio
  )
  lazy val zioConfig = Seq(
    "dev.zio" %% "zio-config"          % Versions.zioConfig,
    "dev.zio" %% "zio-config-typesafe" % Versions.zioConfig,
    "dev.zio" %% "zio-config-magnolia" % Versions.zioConfig
  )
  lazy val zioLogging = Seq(
    "dev.zio"       %% "zio-logging"       % Versions.zioLogging,
    "dev.zio"       %% "zio-logging-slf4j" % Versions.zioLogging,
    "ch.qos.logback" % "logback-classic"   % Versions.logbackClassic
  )

  lazy val zioPrelude = Seq(
    "dev.zio" %% "zio-prelude" % Versions.zioPrelude
  )
  lazy val circe = Seq(
    "io.circe" % "circe-core_2.13"    % Versions.circe,
    "io.circe" % "circe-generic_2.13" % Versions.circe,
    "io.circe" % "circe-parser_2.13"  % Versions.circe
  )
  lazy val scalatest = Seq(
    "org.scalatest" %% "scalatest" % Versions.scalatest
  )

  lazy val zioTest = Seq(
    "dev.zio" %% "zio-test"     % Versions.zio % "test",
    "dev.zio" %% "zio-test-sbt" % Versions.zio % "test"
  )

  lazy val caliban = Seq(
    "com.github.ghostdogpr" %% "caliban"          % Versions.caliban,
    "com.github.ghostdogpr" %% "caliban-zio-http" % Versions.caliban
  )

  lazy val zioHttp = Seq(
    "io.d11" %% "zhttp" % Versions.zioHttp
  )

  lazy val zioStream = Seq(
    "dev.zio" %% "zio-streams" % Versions.zio
  )
}
