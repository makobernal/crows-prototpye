import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "crows-prototype"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // crows-prototype dependencies
    "org.neo4j"%"neo4j"%"1.8.2",
      
    // Original play dependencies
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

}
