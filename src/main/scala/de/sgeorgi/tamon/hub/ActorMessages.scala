package de.sgeorgi.tamon.hub

/**
 * Created by sgeorgi on 15.08.14.
 */
object ActorMessages {
  case object StartSocketServer
  case object StartRestServer
  case class MessageReceived(m: Message)
}
