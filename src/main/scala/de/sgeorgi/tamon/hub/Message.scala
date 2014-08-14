package de.sgeorgi.tamon.hub

/**
 * Created by sgeorgi on 13.08.14.
 */

sealed trait Message {
  val sender: String
  val service: String
  val message: String
}

case class LogMessage(sender: String, service: String, message: String) extends Message

case class UnknownMessage(message: String) extends Message {
  val sender, service = ""
}

object Message {

  object Types {
    val Log = 1: Int
    val Count = 2: Int
    val Unknown = 999: Int
  }

  def decode(incoming: String): Message = {
    def parseMessageType(s: String) = {
      def typeStringToInt(s: String): Int = s match {
        case _ if s.matches("\\d+") => Integer.parseInt(s)
        case _ => 999
      }

      typeStringToInt(s) match {
        case 1 => Types.Log
        case 2 => Types.Count
        case 999 => Types.Unknown
      }
    }

    val elements = incoming.split("\\|")
    val typ = parseMessageType(elements(0))

    typ match {
      case Types.Log => new LogMessage(elements(1), elements(2), elements(3))
      case Types.Unknown => new UnknownMessage(incoming)
    }
  }

}
