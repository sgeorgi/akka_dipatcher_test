package de.sgeorgi.tamon.hub.actors

import akka.actor.{Actor, ActorRef}
import de.sgeorgi.tamon.hub.Service
import spray.routing.HttpService

class RestServiceActor(val dispatcher: ActorRef) extends Actor with Service with HttpService {
  def actorRefFactory = context

  def receive = runRoute(myRoute)

  val myRoute =
    formField("message") {
      (message) =>
        /* Call Logic of Service-Trait*/
        parseAndDispatch(message)

        post {
          complete {
            <html>
              <body>
                <h1>
                  Message received!
                </h1>
              </body>
            </html>
          }
        }
    }
}
