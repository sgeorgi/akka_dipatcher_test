package de.sgeorgi.tamon.hub

import akka.actor.{ActorSystem, Props}
import de.sgeorgi.tamon.hub.actors.DispatcherServiceActor

object Application extends App {
  val actorSystem = ActorSystem("tamon-hub")
  val dispatcher = actorSystem.actorOf(Props(classOf[DispatcherServiceActor]), "dispatcher")
}
