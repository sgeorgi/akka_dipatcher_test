package de.sgeorgi.tamon.hub.actors

import akka.actor.{Props, ActorSystem, Actor}
import akka.io.IO
import akka.util.Timeout
import de.sgeorgi.tamon.hub.modules.ConsoleLogger
import spray.can.Http
import akka.pattern.ask
import scala.concurrent.duration._

/**
 * Created by sgeorgi on 12.08.14.
 */
class Dispatcher extends Actor with ConsoleLogger {

  import de.sgeorgi.tamon.hub.actors.ActorMessages._

  implicit val actorSystem = ActorSystem("tamon-hub")
  implicit val timeout = Timeout(5.seconds)

  def receive = {

    case StartSocketServer() =>
      log("SocketServer started")

    case StartRestServer() =>
      val service = actorSystem.actorOf(Props[RestServiceActor], "rest-service-actor")
      IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
      log("RestServer started")
  }
}
