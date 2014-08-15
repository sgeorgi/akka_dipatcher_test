package de.sgeorgi.tamon.hub

import de.sgeorgi.tamon.hub.Message._

/**
 * Created by sgeorgi on 13.08.14.
 */
class MessageSpec extends UnitSpec {
  describe("Message.decode") {
    it("returns a LogMessage for correct message string") {
      val string = "1|Sender|Service|Message"
      assert(Message.decode(string).isInstanceOf[LogMessage])
    }

    describe("for malformed message") {
      val string_1 = "abc|Sender|Service|Message"
      val string_2 = "xxx|SenderServiceMessage"
      val string_3 = "SenderServiceMessage"

      it("returns a UnknownMessage") {
        assert(Message.decode(string_1).isInstanceOf[UnknownMessage])
        assert(Message.decode(string_2).isInstanceOf[UnknownMessage])
        assert(Message.decode(string_3).isInstanceOf[UnknownMessage])
      }

      it("returns the whole message inside UnkownMessage") {
        assert(Message.decode(string_1).message == string_1)
        assert(Message.decode(string_2).message == string_2)
        assert(Message.decode(string_3).message == string_3)
      }
    }
  }

}
