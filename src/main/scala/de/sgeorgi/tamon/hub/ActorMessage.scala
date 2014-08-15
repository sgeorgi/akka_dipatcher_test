package de.sgeorgi.tamon.hub

/**
 * Object for holding Message classes for communication between Actors
 */
object ActorMessage {
  case class MessageReceived(m: Message)
  case class PersistMessage(m: Message)
  case class LogMessage(m: Message)

}
