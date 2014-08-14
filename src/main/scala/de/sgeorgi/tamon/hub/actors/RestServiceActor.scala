package de.sgeorgi.tamon.hub.actors

import akka.actor.Actor
import de.sgeorgi.tamon.hub.modules.ConsoleLogger
import de.sgeorgi.tamon.hub.{HubSystem, Message}
import spray.routing.HttpService

/**
 * Created by sgeorgi on 13.08.14.
 */
class RestServiceActor extends Actor with RestService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait RestService extends HttpService {
  val callback = (m: Message) => HubSystem.WorkFlow.workOnMessage(m)

  val myRoute =
    formField("message") { (message) =>

      val msg: Message = Message.decode(message)
      callback(msg)

      post {
        complete {
          <html>
            <body>
              <h1>Message received!</h1>
            </body>
          </html>
        }
      }
    }
}
