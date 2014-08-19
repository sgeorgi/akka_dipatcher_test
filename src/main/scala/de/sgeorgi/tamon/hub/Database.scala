package de.sgeorgi.tamon.hub

import reactivemongo.api.DefaultDB

/**
 * Created by sgeorgi on 19.08.14.
 */
object Database {
  def messageCollection: DefaultDB = {
    import reactivemongo.api._

    import scala.concurrent.ExecutionContext.Implicits.global

    val driver = new MongoDriver()
    val connection = driver.connection(List("localhost"))
    val db = connection("tamon_hub")

    connection("messages")
  }
}
