package de.sgeorgi.tamon.hub.modules

/**
 * Central trait for Logging. A Function of (String => Unit) has to be provided as the logging mechanism.
 * See ConsoleLogger for an implementation!
 */
sealed trait Logger {
  val method: (String => Unit)
  def log(s: String): Unit = method(s)
}

/**
 * ConsoleLogger provides a println method to Logging
 */
trait ConsoleLogger extends Logger {
  val method: (String => Unit) = println
}

/**
 * Null logger provives a method 'doNothing' to Logger
 */
trait NullLogger extends Logger {
  val method: (String => Unit) = doNothing
  def doNothing(x: Any): Unit
}