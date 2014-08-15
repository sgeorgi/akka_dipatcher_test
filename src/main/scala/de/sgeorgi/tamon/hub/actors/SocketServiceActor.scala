package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import akka.io.Tcp._
import akka.util.ByteString
import de.sgeorgi.tamon.hub.Service

/**
 * Actor for the Socket service. Listens to bound address/port and spawns a new SocketHandler Actor upon new client
 * connection.
 *
 * @param dispatcher a reference to the calling Actor (the Dispatcher) to send back messages to
 */
class SocketServiceActor(val dispatcher: ActorRef) extends Actor with SocketImplicits {
  def receive = {
    case b@Bound(localAddress) =>
      println("SocketServer started")

    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val handler = context.actorOf(Props(new SocketHandler(dispatcher)))
      val connection = sender()
      connection ! Register(handler)
  }
}

/**
 * Actor for handling a new IO.Tcp socket connection. Handles a line of input and kills itself.
 *
 * @param dispatcher a reference to the calling Actor (the Dispatcher) to send back messages to
 */
class SocketHandler(val dispatcher: ActorRef) extends Actor with Service with SocketImplicits {
  def receive = {
    case Received(data) =>
      parseAndDispatch(data) /* Defined in Service-Trait */
      self ! PoisonPill
    case PeerClosed => context stop self
  }
}

/**
 * Implicit conversions for using String <--> ByteString in a more straight-forward fashion
 */
trait SocketImplicits {
  import scala.language.implicitConversions
  implicit def string2ByteString(s: String): ByteString = ByteString(s)
  implicit def ByteString2String(bs: ByteString): String = bs.decodeString("UTF-8").stripLineEnd
}