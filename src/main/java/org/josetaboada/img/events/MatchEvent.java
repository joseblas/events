package org.josetaboada.img.events;

import org.josetaboada.img.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class MatchEvent {

    private final static Logger logger = LoggerFactory.getLogger(Match.class);

    private Integer team1, team2;
    private Integer timestamp;
    private Integer whoScored;
    private Integer pointsScored;
    transient private String line;

    public MatchEvent(String line) {
        this.line = line;
    }

    public Optional<MatchEvent> apply(){

        try {

            int data = Integer.decode(line);
            pointsScored = BitHelper.POINTS_SCORED.applyTo(data);
            whoScored = BitHelper.WHO_SCORED.applyTo(data) + 1;
            team1 = BitHelper.TEAM1.applyTo(data);
            team2 = BitHelper.TEAM2.applyTo(data);
            timestamp = BitHelper.TIMESTAMP.applyTo(data);

            return Optional.<MatchEvent>of(this);

        }catch(NumberFormatException | NullPointerException e){
            logger.warn( String.format(" Error building an event %s with error %s",line,  e.getMessage()));
            return Optional.empty();
        }
    }

    public Integer getTeam1() {
        return team1;
    }
    public Integer getTeam2() {
        return team2;
    }
    public Integer getTimestamp() {
        return timestamp;
    }
    public Integer getWhoScored() {
        return whoScored;
    }
    public Integer getPointsScored() {
        return pointsScored;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MatchEvent{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", team1=").append(team1);
        sb.append(", team2=").append(team2);
        sb.append(", whoScored=").append(whoScored);
        sb.append(", pointsScored=").append(pointsScored);
        sb.append(", data=").append(line);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchEvent that = (MatchEvent) o;

        if (!team1.equals(that.team1)) return false;
        if (!team2.equals(that.team2)) return false;
        if (!timestamp.equals(that.timestamp)) return false;
        if (!whoScored.equals(that.whoScored)) return false;
        return pointsScored.equals(that.pointsScored);

    }

    @Override
    public int hashCode() {
        int result = team1.hashCode();
        result = 31 * result + team2.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + whoScored.hashCode();
        result = 31 * result + pointsScored.hashCode();
        return result;
    }

    private enum BitHelper {
        POINTS_SCORED(0b11, 0),
        WHO_SCORED(0b1_00, 2),
        TEAM2(0b11111111_0_00, 3),
        TEAM1(0b11111111_00000000_0_00, 11),
        TIMESTAMP(0b111111111111_00000000_00000000_0_00, 19);

        int mask;
        int shift;

        BitHelper(int mask, int shift) {
            this.mask = mask;
            this.shift = shift;
        }

        public int applyTo(int num) {
            return (num & mask) >> shift;
        }
    }
}
