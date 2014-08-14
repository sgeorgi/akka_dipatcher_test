package de.sgeorgi.tamon.hub.services

import de.sgeorgi.tamon.hub.modules.{NullLogger, Persistable}
import de.sgeorgi.tamon.hub.{UnitSpec, Message, System}
import spray.http.{FormData, StatusCodes}
import spray.testkit.ScalatestRouteTest

/**
 * Creating a TestHubSystem and a TestRestService
 */
object TestHubSystem extends System {
  type L = NullLogger
  type P = Persistable

  object WorkFlow extends Orchestrator with NullLogger with Persistable
}

trait TestRestService extends RestService {
  override val callback = (m: Message) => TestHubSystem.WorkFlow.workOnMessage(m)
}

/* The actual Test*/
class RestServiceSpec extends UnitSpec with ScalatestRouteTest with TestRestService {
  def actorRefFactory = actorTestSystem

  describe("RestService") {
    it("responds to a POST request with data") {
      Post("/", FormData(Map("message" -> "1|Sender|Service|Message"))) ~> myRoute ~> check {
        status should be(StatusCodes.OK)
        responseAs[String] should include("Message received!")
      }
    }
  }
}
