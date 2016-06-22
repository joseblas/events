
# Implementation.

 This solution is based on immutable data. All the events will be stored and will be queryable.

 All the messages will be mapped to a data structure: MatchEvent.

 If a message is incorrect will not be stored.

 There is a method 'stream' that makes available all the store in a stream. In order to make all the date available I'd implemented a Rx Stream.


# Asssumsions about inconsistency

 1. If a second message arrives with the same timestamp or older, it will be discarded
 2. If a message arrives and it is inconsistent (last points plus new points are not equals to new total points) with the previuos message, it will be discarded.
