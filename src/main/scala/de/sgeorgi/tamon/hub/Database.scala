package de.sgeorgi.tamon.hub

import de.sgeorgi.tamon.hub.Message.LogMessage
import play.api.libs.iteratee.Iteratee
import reactivemongo.api._
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random


/**
 * Main Database Object that uses {@code ReactiveMongo} to insert Messages into the Database
 */
object Database {
  val driver = new MongoDriver()
  val connection = driver.connection(List("localhost"))
  val db = connection("tamon-hub")
  val collection = db("messages")

  /**
   * Implicit Writer to convert {@code LogMessage} to {@code BSONDocument}
   */
  implicit object LogMessageWriter extends BSONDocumentWriter[LogMessage] {
    def write(message: LogMessage): BSONDocument = BSONDocument(
      "sender" -> message.sender,
      "service" -> message.service,
      "message" -> message.message
    )
  }

  /**
   * Implicit Reader to convert {@code BSONDocument} to {@code LogMessage}
   */
  implicit object LogMessageReader extends BSONDocumentReader[LogMessage] {
    def read(doc: BSONDocument): LogMessage = {
      LogMessage(
        doc.getAs[String]("sender").get,
        doc.getAs[String]("service").get,
        doc.getAs[String]("message").get
      )
    }
  }

  /**
   * Inserts a Message into the collection
   * @param message a LogMessage
   * @return a Future of the operation
   */
  def insertMessage(message: LogMessage) = {
    collection.insert(message)
  }

  def listMessages = {
    val query = BSONDocument()
    val cursor = collection.find(query).cursor[BSONDocument].
      enumerate().apply(Iteratee.foreach { doc =>
      println("found document: " + BSONDocument.pretty(doc))
    })
  }

  def populateRandom(number: Int) = {
    def doMultipleTimes[B](times: Int)(block: => B): B = {
      var counter = 1
      while (counter < times) {
        block
        counter += 1
      }
      block
    }

    val random = new Random(1234567890)

    doMultipleTimes(number) {
      val document = LogMessage(random.nextString(5), random.nextString(10), random.nextString(150))
      collection.insert(document)
    }
  }


}

