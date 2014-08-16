package de.sgeorgi.tamon.hub.actors

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorSystem}
import akka.io.{IO, Tcp}
import akka.pattern.ask
import akka.util.Timeout
import de.sgeorgi.tamon.hub.Dispatcher
import spray.can.Http

import scala.concurrent.duration._

/**
 * Actor for the DispatcherSerice. Implements the Dispatcher interface by providing necessary ActorRefs and Binds
 * outward APIs.
 */
class DispatcherServiceActor extends Actor with Dispatcher {
  implicit val actorSystem = ActorSystem("tamon-hub")
  implicit val timeout = Timeout(5.seconds)

  val persistProcessorRef = context.actorOf(PersistProcessorActor.props(self), "persist-processor")
  val loggingProcessorRef = context.actorOf(LoggingProcessorActor.props(self), "logging-processor")
  val restServiceRef = context.actorOf(RestServiceActor.props(self), "rest-service")
  val socketServiceRef = context.actorOf(SocketServiceActor.props(self), "socket-service")

  IO(Http) ? Http.Bind(restServiceRef, interface = "localhost", port = 8080)
  IO(Tcp) ? Tcp.Bind(socketServiceRef, new InetSocketAddress("localhost", 8081))
}
