package io.github.mavenrain

import io.github.mavenrain.removeElem
import io.r2dbc.spi.ConnectionFactory
import scala.util.chaining.scalaUtilChainingOps
import zio.Runtime.default.unsafeRun
import zio.ZIO
import zio.console.putStrLn

@main
def run =
  given numbersDb: ConnectionFactory = "numbers".toDatabase.connectionFactory
  (for
    rowsUpdated <- Seq(
      "create table numbers (number INT);",
      "insert into numbers values(42);",
      "insert into numbers values(21);",
      "insert into numbers values(84);"
    ).toCommands.execute
    numbers <- "select number from numbers".toQuery.results
    _ <- ZIO.foreach(numbers.map(_.get("number", classOf[Integer])).map(_.toString))(putStrLn(_))
  yield ()).pipe(unsafeRun(_))
  println("Hello world!")
  println(("Hi", true, 2).removeElem[Int]._2)