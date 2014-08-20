package de.sgeorgi.tamon.hub

/**
 * Object for holding Message classes for communication between Actors
 */
object ActorMessage {
  case class AMessageReceived(m: Message)
  case class APersistMessage(m: Message)
  case class ALogMessage(m: Message)

}
