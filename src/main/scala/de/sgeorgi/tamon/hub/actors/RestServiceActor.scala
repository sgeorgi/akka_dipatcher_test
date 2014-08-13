package de.sgeorgi.tamon.hub.actors

import akka.actor.Actor
import spray.routing.HttpService

/**
 * Created by sgeorgi on 13.08.14.
 */
class RestServiceActor extends Actor with RestService {
  def actorRefFactory = context

  def receive = runRoute(myRoute)
}

trait RestService extends HttpService {
  val myRoute =
    get {
      complete {
        <html>
          <body>
            <h1>Tamon HUB Rest API!</h1>
          </body>
        </html>
      }
    }
}
