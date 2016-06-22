package org.josetaboada.img;

import org.josetaboada.img.events.MatchEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class Match {
    private final static Logger logger = LoggerFactory.getLogger(Match.class);
    final private List<MatchEvent> events;

    private Integer errors = 0;

    public Match() {
        events = new ArrayList<>();
    }

    public void push(Optional<MatchEvent> event) {
        if(event.isPresent()) {
            if (isTimelyConsistent(event.get())) {
                logger.warn("Duplicated/inconsistent event: " + event);
                errors++;
                return;
            }
            addElement(event.get());
        }
    }

    private boolean isTimelyConsistent(MatchEvent event) {
        return events.stream().anyMatch(item -> item.getTimestamp() >= event.getTimestamp());
    }

    private void addElement(MatchEvent e) {
        if (!isConsistent(e)) {
            logger.warn("Event inconsistent in totals dropped: " + e);
            errors++;
            return;
        }

        events.add(e);

        logger.info("Added new event: " + e.toString());
    }

    /**
     * returns true if the sum of points is consistent with the latest event
     */
    public boolean isConsistent(MatchEvent e) {
        if (events.isEmpty()){
            return true;
        }

        MatchEvent last = getLastEvent();
        if (e.getWhoScored() == 1) {
            return last.getTeam1() + e.getPointsScored() == e.getTeam1();
        } else {
            return last.getTeam2() + e.getPointsScored() == e.getTeam2();
        }

    }

    public MatchEvent getLastEvent() {
        return events.get(events.size() - 1);
    }

    public List<MatchEvent> getAllEvent() {
        return Collections.unmodifiableList(events);
    }

    public List<MatchEvent> getLastNEvents(int n) {
        if( n < 0 || n > events.size())
            throw new IllegalArgumentException("out of bounds exception. Should be an element between 0 and "+events.size());
        return Collections.unmodifiableList(events.subList(events.size() - n , events.size() ));
    }

    public Stream<MatchEvent> stream(){
        return events.stream();
    }

    public int errors(){
        return errors;
    }

}
