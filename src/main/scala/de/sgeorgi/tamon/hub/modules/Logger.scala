package de.sgeorgi.tamon.hub.modules

/**
 * Central trait for Logging. A Function of (String => Unit) has to be provided as the logging mechanism.
 * See ConsoleLogger for an implementation!
 */
sealed trait Logger {
  type Callback[String] = (String) => Unit

  val logMethod: Callback[String]
  def log(s: String): Unit = logMethod(s)
}

/**
 * ConsoleLogger provides a println method to Logging
 */
trait ConsoleLogger extends Logger {
  val logMethod: Callback[String] = (x: String) => println(x)
}

/**
 * Null logger provives a method 'doNothing' to Logger
 */
trait NullLogger extends Logger {
  val logMethod: Callback[String] = (x: String) => {}
}