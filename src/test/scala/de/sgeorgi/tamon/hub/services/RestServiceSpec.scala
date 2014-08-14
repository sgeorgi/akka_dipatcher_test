package de.sgeorgi.tamon.hub.services

import de.sgeorgi.tamon.hub.{TestHub, UnitSpec}
import spray.http.{FormData, StatusCodes}
import spray.testkit.ScalatestRouteTest


/* The actual Test*/
class RestServiceSpec extends UnitSpec with ScalatestRouteTest with RestService with TestHub {
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
