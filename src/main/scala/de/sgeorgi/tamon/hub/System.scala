package de.sgeorgi.tamon.hub

import de.sgeorgi.tamon.hub.modules._

/**
 * Created by sgeorgi on 13.08.14.
 */
trait System {
  type L <: Logger
  type P <: Persistable

  trait Orchestrator {
    this: L with P =>

    def initialize() = {
      log("Orchestrator initialized")
    }
  }

}

