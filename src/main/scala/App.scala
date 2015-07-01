package acme

import knobs._
import scalaz.concurrent.Task

object App extends App {
    val foo = for {
      config <- loadImmutable(List(ClassPathResource("config.hocon").required))
    } yield {
      println(config.require[String]("key"))
    }
    foo.run
}

