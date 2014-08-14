package de.sgeorgi.tamon.hub.actors

import de.sgeorgi.tamon.hub.Message

/**
 * Created by sgeorgi on 12.08.14.
 */
object ActorMessages {
  case object StartSocketServer
  case object StartRestServer
  case class MessageReceived(m: Message)

}
