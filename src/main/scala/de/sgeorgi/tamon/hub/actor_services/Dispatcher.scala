package de.sgeorgi.tamon.hub.actor_services

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import de.sgeorgi.tamon.hub.ActorMessages._
import spray.can.Http

import scala.concurrent.duration._

class Dispatcher extends Actor with HubService {
  implicit val actorSystem = ActorSystem("tamon-hub")
  implicit val timeout = Timeout(5.seconds)

  val persistProcessorRef = context.actorOf(Props(new PersistProcessor(self)), "persist-processor")
  val loggingProcessorRef = context.actorOf(Props(new LoggingProcessor(self)), "logging-processor")
  val restServiceRef = context.actorOf(Props(new RestService(self)))
  val socketServiceRef = context.actorOf(Props(new SocketService(self)), "socket-service")

  IO(Http) ? Http.Bind(restServiceRef, interface = "localhost", port = 8080)

  def receive = {
    case MessageReceived(message) =>
      println("Dispatcher received Message from '" + message.sender + "'")
  }

}
