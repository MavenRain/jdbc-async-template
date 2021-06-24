lazy val root =
  project
    .in(file("."))
    .settings(
      organization := "io.github.mavenrain",
      name := "jdbc-async-template",
      version := "0.1.0-SNAPSHOT",
      versionScheme := Some("early-semver"),

      scalaVersion := "3.0.0",
      // todo remove when fixed: https://github.com/lampepfl/dotty/issues/11943
      Compile / doc / sources := Seq(),
      libraryDependencies ++= Seq(
        "dev.zio" %% "zio" % "1.0.9",
        "dev.zio" %% "zio-interop-reactivestreams" % "1.3.5",
        "io.r2dbc" % "r2dbc-h2" % "0.8.4.RELEASE"
      )
    )

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}
dockerExposedPorts += 9000
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)