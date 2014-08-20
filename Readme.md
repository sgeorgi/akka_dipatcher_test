# Akka Dispatcher Test

This sample project was used to learn a ton about having multiple Akka Actor orchestrated in a useful way.

The application spawns a Dispatcher-Actor which holds references to 2 receiving Actors (Akka.io and Spray-HTTP) which
parse incoming Messages defined by a Message protocol, and the dispatcher then will pass incoming Messages to another 2 
referenced Actors. On of the processing Actors stores Messages in a MongoDB Database with ReactiveMongo.

This personal learning project is considered finished by the author and will be left for further reference.

## Example Usage

Start an sbt session and type `reStart`.

You can now point your browser to `http://localhost:8080` and POST a message with `?message=...` in the following format:

    1|Sender|Service|Message

or telnet to `localhost:8081` and type in one message a line.

The messages will get stored in the database, the log-processing Actor is currently not implemented.

## Regarding testing...

None but the basic message decoding is tested properly. It's a shame, but learning Scala/Akka is quite hard and
learning how and providing proper tests for the Actors just was not in the given timeframe of this personal education
effort :-/