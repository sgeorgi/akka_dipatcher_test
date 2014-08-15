package de.sgeorgi.tamon.hub.actor_services

import akka.actor.{Actor, ActorRef}

/**
 * Created by sgeorgi on 15.08.14.
 */
class LoggingProcessor(val dispatcher: ActorRef) extends Actor with Processor
