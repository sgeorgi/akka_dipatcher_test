package de.sgeorgi.tamon.hub.actor_services

import akka.actor.{Actor, ActorRef}

class SocketService(val dispatcher: ActorRef) extends Actor with Service {
  def receive = {
    case _ =>
      println("Messag received")
  }
}
