package de.sgeorgi.tamon.hub

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import de.sgeorgi.tamon.hub.actors.{ActorMessages, Dispatcher}

import scala.concurrent.duration._

/**
 * Created by sgeorgi on 12.08.14.
 */
object Application extends App {
  import ActorMessages._

  val actorSystem = ActorSystem("tamon-hub")

  val dispatcher = actorSystem.actorOf(Props(new Dispatcher))
  dispatcher ! StartSocketServer()
  dispatcher ! StartRestServer()
}
