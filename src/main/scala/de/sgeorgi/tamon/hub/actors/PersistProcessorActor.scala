package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef}
import de.sgeorgi.tamon.hub.Processor

class PersistProcessorActor(val dispatcher: ActorRef) extends Processor with Actor
