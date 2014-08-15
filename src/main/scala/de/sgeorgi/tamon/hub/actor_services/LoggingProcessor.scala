package de.sgeorgi.tamon.hub.actor_services

import akka.actor.{Actor, ActorRef}

class LoggingProcessor(val dispatcher: ActorRef) extends Actor with Processor {
  def receive = {
    case _ =>
      println("Messag received")
  }
}
