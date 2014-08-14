package de.sgeorgi.tamon.hub.actors

import akka.actor.{ActorRef, Actor}
import de.sgeorgi.tamon.hub.Message
import de.sgeorgi.tamon.hub.actors.ActorMessages.MessageReceived
import spray.routing.HttpService

/**
 * Created by sgeorgi on 13.08.14.
 */
class RestServiceActor(val dispatcher: ActorRef) extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute(myRoute)

  val myRoute =
    formField("message") {
      (message) =>

        val msg: Message = Message.decode(message)
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


