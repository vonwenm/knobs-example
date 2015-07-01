package acme

import knobs._
import scalaz.concurrent.Task

import java.io.File

case class MyConfig(driver: String, url: String, username: String, password: String)

object App {
  def main(args: Array[String]) {

    val configName = "config.hocon"
    val configPath = new File(cwd, configName)

    val defaultConfigTask = loadImmutable(List(classPathConfig(configName)))
    val fileConfigTask = loadImmutable(List(filePathConfig(configPath)))

    defaultConfigTask.map { defaultConfig =>
      fileConfigTask.map { fileConfig =>
        val config = defaultConfig ++ fileConfig

        val driver: String = config.require[String]("database.driver")
        val url: String = config.require[String]("database.url")
        val username: String = config.require[String]("database.username")
        val password: String = config.require[String]("database.password")

        val myConfig = MyConfig(driver, url, username, password)
        println(myConfig)
        println("foo")
      }.run
    }.run

    val xxx: Task[Unit] = for {
      defaultConfig <- defaultConfigTask
      fileConfig <- fileConfigTask
    } yield {
        val config = defaultConfig ++ fileConfig

        val driver: String = config.require[String]("database.driver")
        val url: String = config.require[String]("database.url")
        val username: String = config.require[String]("database.username")
        val password: String = config.require[String]("database.password")

        val myConfig = MyConfig(driver, url, username, password)
        println(myConfig)
        println("bar")
      }
    xxx.run
  }

  def filePathConfig(path: File) = {
    FileResource(path).optional
  }

  def classPathConfig(configName: String) = {
    val configPath = configName
    ClassPathResource(configPath).required
  }

  def cwd = new File(".").getCanonicalFile()

}

