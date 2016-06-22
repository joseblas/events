package org.josetaboada.img;

import org.josetaboada.img.events.MatchEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jta20 on 21/06/2016.
 */
public class MatchTest {

    Match match;

    @Before
    public void setUp() {
        match = new Match();
    }

    @Test
    public void testMatch1_examples() {
        match.push(new MatchEvent("0x781002").apply());
        match.push(new MatchEvent("0xf0101f").apply());
//        match.isConsistent();
    }

    @Test
    public void testMatch1Inconsistency_by_time_inconsistency() {
        match.push(new MatchEvent("0xf0101f").apply());
        match.push(new MatchEvent("0x781002").apply());
        assertThat("There are onw two messages", match.getAllEvent().size(), is(1));
//        match.isConsistent();
    }

    @Test
    public void testMatch1Inconsistency_by_duplication() {
        match.push(new MatchEvent("0x781002").apply());
        match.push(new MatchEvent("0x781002").apply());
        match.push(new MatchEvent("0xf0101f").apply());

    }


    @Test
    public void testMatch1_1() {
        match.push(new MatchEvent("0x781002").apply());
        match.push(new MatchEvent("0xf0101f").apply());
        System.out.println(" Final Result: " + match.getLastEvent());
    }

    @Test
    public void testGetAllEvents() {
        playMatch1(match);
        assertThat("Has event", match.getAllEvent().size(), is(28));
    }

    @Test
    public void testGetLastEvent() {
        playMatch1(match);
        assertThat("Has event", Optional.of(match.getLastEvent()), is(new MatchEvent("0x12b0d8ea").apply()));
        assertThat("Event is present ", Optional.of(match.getLastEvent()).isPresent(), is(true));
    }

    @Test
    public void testGetNEvent_1() {
        playMatch1(match);

        assertThat("Gets one event only", match.getLastNEvents(1).size(), is(1));
        assertThat("Has event", match.getLastNEvents(1), is(Arrays.asList( new MatchEvent("0x12b0d8ea").apply().get())));
    }

    @Test
    public void testGetNEvent_2() {
        playMatch1(match);

        assertThat("Gets one event only", match.getLastNEvents(2).size(), is(2));
        assertThat("Has event", match.getLastNEvents(2), is(Arrays.asList(
                new MatchEvent("0x1228c8ea").apply().get(),
                new MatchEvent("0x12b0d8ea").apply().get()))
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNEvent_more_than_elements() {
        playMatch1(match);
        match.getLastNEvents(200);

    }

    @Test
    public void testMatch1WithNoErrors() {
        playMatch1(match);
        assertThat("Has events ", match.getAllEvent().size(), is(28));
        assertThat("No errors ", match.errors(), is(0));
    }

    @Test
    public void testMatch2WithErrors() {
        playMatch2(match);
        assertThat("Has events ", match.getAllEvent().size(), is(25));
        assertThat("No errors ", match.errors(), is(5));
    }


    @Test
    public void testMatch2WithEmptyLines() {
        playMatch2(match);
        match.push(new MatchEvent("").apply());
        assertThat("Has events ", match.getAllEvent().size(), is(25));
        assertThat("No errors ", match.errors(), is(5));
    }

    private void playMatch2(Match match) {
        match.push(new MatchEvent("0x781002").apply());
        match.push(new MatchEvent("0xe01016").apply());
        match.push(new MatchEvent("0x1081014").apply());
        match.push(new MatchEvent("0x1e0102f").apply());
        match.push(new MatchEvent("0x258202a").apply());
        match.push(new MatchEvent("0x308203e").apply());
        match.push(new MatchEvent("0x388204e").apply());
        match.push(new MatchEvent("0x388204e").apply());
        match.push(new MatchEvent("0x3d0384b").apply());
        match.push(new MatchEvent("0x478385e").apply());
        match.push(new MatchEvent("0x618406e").apply());
        match.push(new MatchEvent("0x5404059").apply());
        match.push(new MatchEvent("0x6b8506a").apply());
        match.push(new MatchEvent("0x750706c").apply());
        match.push(new MatchEvent("0x7d8507e").apply());
        match.push(new MatchEvent("0x938608e").apply());
        match.push(new MatchEvent("0x8b8607a").apply());
        match.push(new MatchEvent("0xa10609e").apply());
        match.push(new MatchEvent("0xb8870a2").apply());
        match.push(new MatchEvent("0xc4870b6").apply());
        match.push(new MatchEvent("0xcc070c6").apply());
        match.push(new MatchEvent("0x2ee74753").apply());
        match.push(new MatchEvent("0xd5080c2").apply());
        match.push(new MatchEvent("0xdf080d6").apply());
        match.push(new MatchEvent("0xe4098d3").apply());
        match.push(new MatchEvent("0xec098f6").apply());
        match.push(new MatchEvent("0xfc8a8e2").apply());
        match.push(new MatchEvent("0x10a8a8ed").apply());
        match.push(new MatchEvent("0x1180b8ea").apply());
        match.push(new MatchEvent("0x1218c8ea").apply());

    }


    private void playMatch1(Match match) {
        match.push(new MatchEvent("0x801002").apply());
        match.push(new MatchEvent("0xf81016").apply());
        match.push(new MatchEvent("0x1d8102f").apply());
        match.push(new MatchEvent("0x248202a").apply());
        match.push(new MatchEvent("0x2e0203e").apply());//5
        match.push(new MatchEvent("0x348204e").apply());
        match.push(new MatchEvent("0x3b8384b").apply());
        match.push(new MatchEvent("0x468385e").apply());
        match.push(new MatchEvent("0x5304059").apply());
        match.push(new MatchEvent("0x640406e").apply());//10
        match.push(new MatchEvent("0x6d8506a").apply());
        match.push(new MatchEvent("0x760606a").apply());
        match.push(new MatchEvent("0x838607e").apply());
        match.push(new MatchEvent("0x8e8707a").apply());
        match.push(new MatchEvent("0x930708e").apply());//15
        match.push(new MatchEvent("0x9f0709e").apply());
        match.push(new MatchEvent("0xad070a5").apply());
        match.push(new MatchEvent("0xb7880a2").apply());
        match.push(new MatchEvent("0xbf880b6").apply());
        match.push(new MatchEvent("0xc9080c6").apply());//20
        match.push(new MatchEvent("0xd2090c2").apply());
        match.push(new MatchEvent("0xdd090d6").apply());
        match.push(new MatchEvent("0xed0a8d3").apply());
        match.push(new MatchEvent("0xf98a8e6").apply());
        match.push(new MatchEvent("0x10a8b8e2").apply());//25
        match.push(new MatchEvent("0x1178b8ed").apply());
        match.push(new MatchEvent("0x1228c8ea").apply());
        match.push(new MatchEvent("0x12b0d8ea").apply());//28
    }
}