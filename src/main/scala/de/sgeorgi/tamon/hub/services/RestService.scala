package de.sgeorgi.tamon.hub.services

import de.sgeorgi.tamon.hub.{HubSystem, Message}
import spray.routing.HttpService

/**
 * Created by sgeorgi on 14.08.14.
 */
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
