package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef}
import de.sgeorgi.tamon.hub.Processor

/**
 * Created by sgeorgi on 15.08.14.
 */
class LoggingProcessor(val dispatcher: ActorRef) extends Actor with Processor
