# TAMON HUB

This construct will once provide both a REST- and a Socket-API to log and persist incoming Messages (protocol yet to
be established), as well as a queue-system to forward incoming messages to other clients (e.g. a WebSocket).

Goal of this project is to parse, store and forward information from clients in real-time, utilizing maximum 
concurrency with Akka Actors.

