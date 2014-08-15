package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import de.sgeorgi.tamon.hub.Dispatcher
import spray.can.Http

import scala.concurrent.duration._

class DispatcherService extends Actor with Dispatcher {
  implicit val actorSystem = ActorSystem("tamon-hub")
  implicit val timeout = Timeout(5.seconds)

  val persistProcessorRef = context.actorOf(Props(new PersistProcessor(self)), "persist-processor")
  val loggingProcessorRef = context.actorOf(Props(new LoggingProcessor(self)), "logging-processor")
  val restServiceRef = context.actorOf(Props(new RestService(self)))
  val socketServiceRef = context.actorOf(Props(new SocketService(self)), "socket-service")

  IO(Http) ? Http.Bind(restServiceRef, interface = "localhost", port = 8080)
}
