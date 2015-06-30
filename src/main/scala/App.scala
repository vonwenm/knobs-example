package acme

import knobs._

case class MyConfig(driver: String, url: String, username: String, password: String)

object App {
  def main(args: Array[String]) {

    // load some configuration
    val configPath = "config.hocon"

    val config: Config = loadImmutable(List(ClassPathResource(configPath).required)).run

    val driver: String = config.require[String]("database.driver")
    val url: String = config.require[String]("database.url")
    val username: String = config.require[String]("database.username")
    val password: String = config.require[String]("database.password")

    val myConfig = MyConfig(driver, url, username, password)
    println(myConfig)
  }
}
