package de.sgeorgi.tamon.hub

import de.sgeorgi.tamon.hub.modules._

/**
 * Created by sgeorgi on 13.08.14.
 */
trait System {
  type L <: Logger
  type P <: Persistable

  trait Orchestrator {
    this: L with P =>

    def workOnMessage(m: Message) = {
      log("Reveived Message from " + m.sender + " for Service " + m.service + ": " + m.message)
      persist(m)
    }
  }
}

object HubSystem extends System {
  type L = ConsoleLogger
  type P = Persistable

  object WorkFlow extends Orchestrator with ConsoleLogger with Persistable

  def apply(m: Message) = WorkFlow.workOnMessage(m)
}

object TestHubSystem extends System {
  type L = NullLogger
  type P = Persistable

  object WorkFlow extends Orchestrator with NullLogger with Persistable

  def apply(m: Message) = WorkFlow.workOnMessage(m)
}