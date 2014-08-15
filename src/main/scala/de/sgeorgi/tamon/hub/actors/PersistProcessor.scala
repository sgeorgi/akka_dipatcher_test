package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef}
import de.sgeorgi.tamon.hub.Processor

class PersistProcessor(val dispatcher: ActorRef) extends Processor with Actor
