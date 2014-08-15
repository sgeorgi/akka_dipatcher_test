package de.sgeorgi.tamon.hub

import de.sgeorgi.tamon.hub.IncomingMessage._

/**
 * Created by sgeorgi on 13.08.14.
 */
class MessageSpec extends UnitSpec {
  describe("Message.decode") {
    it("returns a LogMessage for correct message string") {
      val string = "1|Sender|Service|Message"
      assert(IncomingMessage.decode(string).isInstanceOf[LogMessage])
    }

    describe("for malformed message") {
      val string_1 = "abc|Sender|Service|Message"
      val string_2 = "xxx|SenderServiceMessage"
      val string_3 = "SenderServiceMessage"

      it("returns a UnknownMessage") {
        assert(IncomingMessage.decode(string_1).isInstanceOf[UnknownMessage])
        assert(IncomingMessage.decode(string_2).isInstanceOf[UnknownMessage])
        assert(IncomingMessage.decode(string_3).isInstanceOf[UnknownMessage])
      }

      it("returns the whole message inside UnkownMessage") {
        assert(IncomingMessage.decode(string_1).message == string_1)
        assert(IncomingMessage.decode(string_2).message == string_2)
        assert(IncomingMessage.decode(string_3).message == string_3)
      }
    }
  }

}
