package de.sgeorgi.tamon.hub.actor_services

import akka.actor.{Actor, ActorRef}

class PersistProcessor(val dispatcher: ActorRef) extends Processor with Actor {
  def receive = {
    case _ =>
      println("Messag received")
  }
}
