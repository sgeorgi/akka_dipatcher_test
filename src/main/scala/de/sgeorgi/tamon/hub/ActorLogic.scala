package de.sgeorgi.tamon.hub

import akka.actor.Actor.Receive
import akka.actor.ActorRef

/**
 * Main Service Trait for incoming message handler Actors.
 * Implementing classes need a reference to the dispatching Actor. A method to parse and dispatch a String input
 * is provided.
 */
trait Service {
  val dispatcher: ActorRef

  def parseAndDispatch(messageString: String): Unit = {
    val message: Message = Message.decode(messageString)
    message match {
      case m: Message.LogMessage => dispatcher ! ActorMessage.MessageReceived(m)
      case m: Message.UnknownMessage =>
    }
  }
}

/**
 * Main Processor Trait for processor Actors handling dispatched messages.
 * Implementing classes need a reference to the dispatching Actor. Default behaviour of implementing classes
 * are specified according to message type they receive (see Dispatcher Trait)
 */
trait Processor {
  val dispatcher: ActorRef

  def receive: Receive = {
    case ActorMessage.PersistMessage(m: Message) =>
      println("Received IncomingMessage for persisting")
    case ActorMessage.LogMessage(m: Message) =>
      println("Received IncomingMessage for logging")
  }
}

/**
 * Main Dispatcher Trait for the dispatching Actor. Implementations will need to provide listed service- and processor
 * ActorRefs. Provides default logic for handling incoming messages and dispatching to child Actors.
 *
 * This might well include the usage of Actor pools/pooling in the future
 */
trait Dispatcher {
  val restServiceRef: ActorRef
  val socketServiceRef: ActorRef
  val persistProcessorRef: ActorRef
  val loggingProcessorRef: ActorRef

  def receive: Receive = {
    case ActorMessage.MessageReceived(message) =>
      println("Dispatcher received Message from '" + message.sender + "', dispatching to Processors")
      persistProcessorRef ! ActorMessage.PersistMessage(message)
      loggingProcessorRef ! ActorMessage.LogMessage(message)
  }
}
