package de.sgeorgi.tamon.hub

/**
 * Created by sgeorgi on 15.08.14.
 */
object ActorMessages {
  case class MessageReceived(m: IncomingMessage)
  case class PersistMessage(m: IncomingMessage)
  case class LogMessage(m: IncomingMessage)

}
