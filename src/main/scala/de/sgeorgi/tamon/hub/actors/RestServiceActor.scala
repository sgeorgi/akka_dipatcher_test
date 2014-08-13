package de.sgeorgi.tamon.hub.actors

import akka.actor.Actor
import de.sgeorgi.tamon.hub.modules.ConsoleLogger
import spray.routing.HttpService

/**
 * Created by sgeorgi on 13.08.14.
 */
class RestServiceActor extends Actor with RestService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait RestService extends HttpService with ConsoleLogger {
  val myRoute =
    get {
      log("Responding to / GET request")
      complete {
        <html>
          <body>
            <h1>Tamon HUB Rest API!</h1>
          </body>
        </html>
      }
    }
}
