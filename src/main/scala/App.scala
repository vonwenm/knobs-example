package acme

import knobs._
import scalaz.concurrent.Task

case class MyConfig(driver: String, url: String, username: String, password: String)

object App {
  def main(args: Array[String]) {

    val configTask: Task[Config] = loadImmutable(List(classPathConfig))
    configTask.map { config =>
      val driver: String = config.require[String]("database.driver")
      val url: String = config.require[String]("database.url")
      val username: String = config.require[String]("database.username")
      val password: String = config.require[String]("database.password")

      val myConfig = MyConfig(driver, url, username, password)
      println(myConfig)
    }.run
  }

  def classPathConfig = {
    val configPath = "config.hocon"
    ClassPathResource(configPath).required
  }

}

