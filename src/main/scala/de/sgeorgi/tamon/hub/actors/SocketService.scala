package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef}
import de.sgeorgi.tamon.hub.Service

class SocketService(val dispatcher: ActorRef) extends Actor with Service {
  def receive = {
    case _ =>
      println("Messag received")
  }
}
