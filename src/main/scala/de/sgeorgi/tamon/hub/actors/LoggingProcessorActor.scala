package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef, Props}
import de.sgeorgi.tamon.hub.Processor

/**
 * Created by sgeorgi on 15.08.14.
 */
object LoggingProcessorActor {
  def props(dispatcher: ActorRef): Props = Props(new LoggingProcessorActor(dispatcher))
}
class LoggingProcessorActor(val dispatcher: ActorRef) extends Actor with Processor
