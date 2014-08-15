package de.sgeorgi.tamon.hub

import akka.actor.{ActorSystem, Props}
import de.sgeorgi.tamon.hub.actor_services.Dispatcher

object Application extends App {
  val actorSystem = ActorSystem("tamon-hub")
  val dispatcher = actorSystem.actorOf(Props(classOf[Dispatcher]), "dispatcher")
}
