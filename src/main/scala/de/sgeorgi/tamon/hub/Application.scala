package de.sgeorgi.tamon.hub

import akka.actor.{ActorSystem, Props}
import de.sgeorgi.tamon.hub.actors.Dispatcher

/**
 * Created by sgeorgi on 12.08.14.
 */
object Application extends App {

  import de.sgeorgi.tamon.hub.actors.ActorMessages._

  val actorSystem = ActorSystem("tamon-hub")

  val dispatcher = actorSystem.actorOf(Props(new Dispatcher with Hub), "dispatcher")
  dispatcher ! StartSocketServer
  dispatcher ! StartRestServer
}
