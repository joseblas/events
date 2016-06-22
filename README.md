
# Implementation.

 This solution will be based on Immutable data, so each message will be considered as an immutable event. All the events
 will be stored and will be queryable.

 All the messages will be mapped to a data structure: MatchEvent.

 When is mentioned that the data has to be sent downstream as soon as possible there is a method provided 'stream()' that return the stream for the the EventStore.
 Another opton would be a RxJava stream, but as is not used in the exercise I have not implemented it.

# Asssumsions about inconsistency

 1. If a second message arrives with the same timestamp or previuos, it will be discarded
 2. If a message arrives and it is inconsistent (last points plus new points are not equals to new total points) with the previuos message, it will be discarded.
