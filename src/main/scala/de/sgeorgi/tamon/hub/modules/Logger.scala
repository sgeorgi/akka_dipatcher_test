package de.sgeorgi.tamon.hub.modules

sealed trait Logger {
  def log(s: String): Unit
}

class ConsoleLogger extends Logger {
  def log(s: String) = println(s)
}

class NullLogger extends Logger {
  def log(s: String) = println("TEST LOG: " + s)
}