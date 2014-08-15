package de.sgeorgi.tamon.hub

/**
 * Created by sgeorgi on 15.08.14.
 */
object ActorMessage {
  case class MessageReceived(m: Message)
  case class PersistMessage(m: Message)
  case class LogMessage(m: Message)

}
