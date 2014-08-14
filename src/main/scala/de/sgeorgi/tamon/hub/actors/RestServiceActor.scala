package de.sgeorgi.tamon.hub.actors

import akka.actor.Actor
import de.sgeorgi.tamon.hub.Hub
import de.sgeorgi.tamon.hub.services.RestService

/**
 * Created by sgeorgi on 13.08.14.
 */
class RestServiceActor extends Actor with RestService with Hub {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}


