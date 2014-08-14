package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import de.sgeorgi.tamon.hub.modules.{Logger, ConsoleLogger}
import spray.can.Http

import scala.concurrent.duration._

/**
 * Created by sgeorgi on 12.08.14.
 */
class Dispatcher extends Actor  {
  import de.sgeorgi.tamon.hub.actors.ActorMessages._

  implicit val actorSystem = ActorSystem("tamon-hub")
  implicit val timeout = Timeout(5.seconds)
  val logger = new ConsoleLogger

  def receive = {

    case StartSocketServer() =>
      logger.log("SocketServer started")

    case StartRestServer() =>
      val service = actorSystem.actorOf(Props(new RestServiceActor), "rest-service-actor")
      IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
      logger.log("RestServer started")
  }
}
