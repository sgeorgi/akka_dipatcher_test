package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef, Props}
import de.sgeorgi.tamon.hub.Processor

object PersistProcessorActor {
  def props(dispatcher: ActorRef): Props = Props(new PersistProcessorActor(dispatcher))
}

class PersistProcessorActor(val dispatcher: ActorRef) extends Processor with Actor
