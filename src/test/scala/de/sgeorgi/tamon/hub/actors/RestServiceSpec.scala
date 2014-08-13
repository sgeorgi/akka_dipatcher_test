package de.sgeorgi.tamon.hub.actors

import de.sgeorgi.tamon.hub.UnitSpec
import spray.testkit.ScalatestRouteTest

/**
 * Created by sgeorgi on 13.08.14.
 */
class RestServiceSpec extends UnitSpec with ScalatestRouteTest with RestService {
  def actorRefFactory = actorTestSystem

  describe("RestService") {
    it("responds to a GET(/)") {
      Get() ~> myRoute ~> check {
        responseAs[String] should include("Tamon HUB Rest API!")
      }
    }
  }

}
