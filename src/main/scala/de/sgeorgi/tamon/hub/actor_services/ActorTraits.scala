package de.sgeorgi.tamon.hub.actor_services

import akka.actor.ActorRef

/**
 * Created by sgeorgi on 15.08.14.
 */
trait Service {
  val dispatcher: ActorRef
}

trait Processor {
  val dispatcher: ActorRef
}

trait HubService {
  val restServiceRef: ActorRef
  val socketServiceRef: ActorRef
  val persistProcessorRef: ActorRef
  val loggingProcessorRef: ActorRef
}
