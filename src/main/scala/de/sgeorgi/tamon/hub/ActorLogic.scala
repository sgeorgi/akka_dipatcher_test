package de.sgeorgi.tamon.hub

import akka.actor.Actor.Receive
import akka.actor.ActorRef
import de.sgeorgi.tamon.hub.ActorMessages.{LogMessage, MessageReceived, PersistMessage}

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

trait Dispatcher {
  val restServiceRef: ActorRef
  val socketServiceRef: ActorRef
  val persistProcessorRef: ActorRef
  val loggingProcessorRef: ActorRef

  def receive: Receive = {
    case MessageReceived(message) =>
      println("Dispatcher received Message from '" + message.sender + "', dispatching to Processors")
      persistProcessorRef ! PersistMessage(message)
      loggingProcessorRef ! LogMessage(message)
  }
}
