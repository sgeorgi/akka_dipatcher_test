package de.sgeorgi.tamon.hub.modules

import de.sgeorgi.tamon.hub.Message

/**
 * Created by sgeorgi on 13.08.14.
 */
trait Persistor {
  def persist(m: Message): Unit = {}
}
