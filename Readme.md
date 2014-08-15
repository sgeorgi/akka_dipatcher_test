# TAMON HUB

## Technical Activity Monitor

This Software is a mingle-mangle of various Akka Actors. It aims to provide a simple and clear API for pushing
various Messages against Service that returns instantly. The message itself will be parsed and handed down to a 
Dispatcher Actor that coordinates further actions.

## Getting started

Simply run sbt and type in `reStart`. See Service APIs description below for pushing messages!



## Technical Overview
### The Message Protocol

Message are (right now) defined as a String in the format of
    
`Sender|Service|Message`
    
where each part (Sender, Service, Message) can be an arbitraty String without the |-caracher. | is used for splitting
up the incoming String into parts.

### Service APIs (aka Services)

As of right now, there are two Services that accept messages: A REST-POST-Interface (done with the help of Spray.io), 
as well as a TCP Socket server (done with Akka.io).

Unless configured otherwise, you can reach the REST-API via `http://localhost:8080` and the socket API via `localhost:8081`

The general logic of a Service is declared in the Service-trait in ActorLogic.scala

### Message Processing (aka Processors)

There are Actor-stubs for persisting incoming messages in a NoSQL database, as well as file-logging Processor. 
The general logic of a Processor is declared in the Processor-trait in ActorLogic.scala

### The Dispatcher

The main Actor starting up is the Dispatcher, which itself creates ActorRefs for all implemented Services and Processors.
The general logic of the Dispatcher is declared in the Dispatcher-trait in ActorLogic.scala.



## Goals and Future Plans

Testing Actors and ActorSystems apparently is no witchcraft, still this projects misses all but the very easy message
decoding Specs. That surely has to change!

I'm rather new at Scala, so the many ways of dependency injection is still hard to grasp and to employ properly. There
 probably will be lots of rather big refactorings to accomodate to DI + testability.
 
### Next up
 * Make the PersistProcessor DB aware and store incoming messages
 * Utilize some fancy 4j-loggin mechanism for the LoggingProcessor to yield useful results