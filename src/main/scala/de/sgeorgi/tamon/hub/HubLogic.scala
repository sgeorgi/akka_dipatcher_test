package de.sgeorgi.tamon.hub

import de.sgeorgi.tamon.hub.modules._

/**
 * Sealed trait for main logic of the Hub. Specialized traits can extend HubLogic and provide their own dependencies,
 * as e.g. for production or testing environment
 */
sealed trait HubLogic {
  val logger: Logger
  val persistor: Persistor

  def workOnMessage(m: Message) = {
    logger.log("Reveived Message from " + m.sender + " for Service " + m.service + ": " + m.message)
    persistor.persist(m)
  }
}

trait Hub extends HubLogic {
  val logger = new ConsoleLogger
  val persistor = new Persistor {}
}

trait TestHub extends HubLogic {
  val logger = new NullLogger
  val persistor = new Persistor {}
}