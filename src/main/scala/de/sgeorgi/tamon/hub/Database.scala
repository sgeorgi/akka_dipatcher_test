package de.sgeorgi.tamon.hub

import play.api.libs.iteratee.Iteratee
import reactivemongo.api._
import reactivemongo.bson.BSONDocument

/**
 * Created by sgeorgi on 19.08.14.
 */
object Database {

  import scala.concurrent.ExecutionContext.Implicits.global

  val driver = new MongoDriver()
  val connection = driver.connection(List("localhost"))
  val db = connection("tamon-hub")
  val collection = db("messages")

  def listMessages() = {
    val query = BSONDocument()
    val cursor = collection.find(query).cursor[BSONDocument].
      enumerate().apply(Iteratee.foreach { doc =>
      println("found document: " + BSONDocument.pretty(doc))
    })
  }

}
