package de.sgeorgi.tamon.hub

import akka.actor.Actor.Receive
import akka.actor.ActorRef

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

trait Processor {
  val dispatcher: ActorRef

  def receive: Receive = {
    case ActorMessage.PersistMessage(m: Message) =>
      println("Received IncomingMessage for persisting")
    case ActorMessage.LogMessage(m: Message) =>
      println("Received IncomingMessage for logging")
  }
}

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
