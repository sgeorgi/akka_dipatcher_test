package de.sgeorgi.tamon.hub.actor_services

import akka.actor.Actor.Receive
import akka.actor.ActorRef
import de.sgeorgi.tamon.hub.ActorMessages.{LogMessage, PersistMessage, MessageReceived}
import de.sgeorgi.tamon.hub.IncomingMessage

/**
 * Created by sgeorgi on 15.08.14.
 */
trait Service {
  val dispatcher: ActorRef

  def parseAndDispatch(m: String): Unit = {
    val msg: IncomingMessage = IncomingMessage.decode(m)
    dispatcher ! MessageReceived(msg)
  }
}

trait Processor {
  val dispatcher: ActorRef

  def receive: Receive = {
    case PersistMessage(m: IncomingMessage) =>
      println("Received IncomingMessage for persisting")
    case LogMessage(m: IncomingMessage) =>
      println("Received IncomingMessage for logging")
  }
}

trait HubService {
  val restServiceRef: ActorRef
  val socketServiceRef: ActorRef
  val persistProcessorRef: ActorRef
  val loggingProcessorRef: ActorRef

  def receive: Receive = {
    case MessageReceived(message) =>
      println("Dispatcher received Message from '" + message.sender + "'")
      persistProcessorRef ! PersistMessage(message)
      loggingProcessorRef ! LogMessage(message)
  }
}
