package de.sgeorgi.tamon.hub.modules

import de.sgeorgi.tamon.hub.Message

/**
 * Created by sgeorgi on 13.08.14.
 */
trait Persistable {
  def persist(m: Message): Unit = {}
}
