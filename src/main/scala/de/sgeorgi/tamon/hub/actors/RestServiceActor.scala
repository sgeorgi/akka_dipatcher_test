package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef, Props}
import de.sgeorgi.tamon.hub.Service
import spray.routing.HttpService

object RestServiceActor {
  def props(dispatcher: ActorRef): Props = Props(new RestServiceActor(dispatcher))
}

/**
 * Actor for the Rest Service. Extends HttpService and provides an API for POSTing a message that will be handled.
 *
 * @param dispatcher a reference to the calling Actor (the Dispatcher) to send back messages to
 */
class RestServiceActor(val dispatcher: ActorRef) extends Actor with Service with HttpService {
  def actorRefFactory = context

  def receive = runRoute(myRoute)

  val myRoute =
    formField("message") {
      (message) =>
        parseAndDispatch(message) /* Defined in Service-Trait */
        post(complete("Message received!"))
    }
}
