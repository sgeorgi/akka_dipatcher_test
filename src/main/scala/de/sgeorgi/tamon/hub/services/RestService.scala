package de.sgeorgi.tamon.hub.services

import akka.actor.ActorRefFactory
import de.sgeorgi.tamon.hub.modules.{NullLogger, Persistor}
import de.sgeorgi.tamon.hub.{HubLogic, Message}
import spray.routing.HttpService

/**
 * Created by sgeorgi on 14.08.14.
 */
trait RestService extends HttpService {
  this: HubLogic =>
  val myRoute =
    formField("message") { (message) =>

      val msg: Message = Message.decode(message)
      workOnMessage(msg)

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
