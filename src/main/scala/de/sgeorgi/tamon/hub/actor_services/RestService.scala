package de.sgeorgi.tamon.hub.actor_services

import akka.actor.{Actor, ActorRef}
import de.sgeorgi.tamon.hub.ActorMessages.MessageReceived
import de.sgeorgi.tamon.hub.IncomingMessage
import spray.routing.HttpService

class RestService(val dispatcher: ActorRef) extends Actor with Service with HttpService {
  def actorRefFactory = context

  def receive = runRoute(myRoute)

  val myRoute =
    formField("message") {
      (message) =>

        val msg: IncomingMessage = IncomingMessage.decode(message)
        dispatcher ! MessageReceived(msg)

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
