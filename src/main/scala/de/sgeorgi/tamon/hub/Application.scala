package de.sgeorgi.tamon.hub

import akka.actor.{ActorSystem, Props}
import de.sgeorgi.tamon.hub.actors.DispatcherService

object Application extends App {
  val actorSystem = ActorSystem("tamon-hub")
  val dispatcher = actorSystem.actorOf(Props(classOf[DispatcherService]), "dispatcher")
}
