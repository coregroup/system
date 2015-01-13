name := """play-java-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJdbc,
  javaWs,
  javaJpa,
  "mysql" % "mysql-connector-java" % "5.1.31",
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"
)