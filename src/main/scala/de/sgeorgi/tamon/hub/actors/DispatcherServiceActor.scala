package de.sgeorgi.tamon.hub.actors

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.{IO, Tcp}
import akka.pattern.ask
import akka.util.Timeout
import de.sgeorgi.tamon.hub.Dispatcher
import spray.can.Http

import scala.concurrent.duration._

class DispatcherServiceActor extends Actor with Dispatcher {
  implicit val actorSystem = ActorSystem("tamon-hub")
  implicit val timeout = Timeout(5.seconds)

  val persistProcessorRef = context.actorOf(Props(new PersistProcessorActor(self)), "persist-processor")
  val loggingProcessorRef = context.actorOf(Props(new LoggingProcessorActor(self)), "logging-processor")
  val restServiceRef = context.actorOf(Props(new RestServiceActor(self)))
  val socketServiceRef = context.actorOf(Props(new SocketServiceActor(self)), "socket-service")

  IO(Http) ? Http.Bind(restServiceRef, interface = "localhost", port = 8080)
  IO(Tcp) ? Tcp.Bind(socketServiceRef, new InetSocketAddress("localhost", 8081))
}
