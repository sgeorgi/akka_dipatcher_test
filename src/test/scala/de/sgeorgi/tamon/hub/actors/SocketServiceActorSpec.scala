package de.sgeorgi.tamon.hub.actors

import akka.actor.{ActorSystem, Props}
import akka.testkit.TestKit
import de.sgeorgi.tamon.hub.StopSystemAfterAll
import org.scalatest.{FunSpecLike, Matchers}

/**
  * Created by sgeorgi on 19.08.14.
  */
class SocketServiceActorSpec extends TestKit(ActorSystem("testsystem")) with FunSpecLike with Matchers with StopSystemAfterAll {
   describe("The RestServiceActor") {
     describe("sends a message to the Dispatcher when it processed a request") {
       val props = Props(new SocketServiceActor(testActor))
       val dispatcher = system.actorOf(props, "socket-actor")
       pending
     }
   }
 }
