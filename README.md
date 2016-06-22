
# Implementation.

 This solution will be based on Event Sourcing. Each message will be considered as an immutable event. All the events
 will be stored and will be queryable.

 All the messages will be mapped to a data structure: ResultDelta. DataParse is responsibe for that transformation.


# Asssumsions about inconsistency

 1. If a second message arrives with the same timestamp or previuos, it will be discanded
 2. If a message arrives and it is inconsistent (last points plus new points are equals to new total points) with the previuos message, it will be discarded.
