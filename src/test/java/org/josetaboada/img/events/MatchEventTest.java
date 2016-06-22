package org.josetaboada.img.events;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by jta20 on 22/06/2016.
 */
public class MatchEventTest {

    @Test
    public void testConstructor(){
        //If after 15 seconds of play, Team 1 scores 2 points, then the following will be received
        Optional<MatchEvent> event = new MatchEvent("0x781002").apply();
        System.out.println(event.get());
        assertThat(event.get().getTimestamp(), is(15));
        assertThat(event.get().getTeam1(), is(2));
        assertThat(event.get().getTeam2(), is(0));
        assertThat(event.get().getWhoScored(), is(1));
        assertThat(event.get().getPointsScored(), is(2));
    }

    @Test
    public void testConstructor_2(){
//        If 15 seconds later, Team 2 replies with 3 points, then the following will be received:
        Optional<MatchEvent> event = new MatchEvent("0xf81016").apply();
        System.out.println(event);
        assertThat(event.get().getTimestamp(), is(31));
        assertThat(event.get().getTeam1(), is(2));
        assertThat(event.get().getTeam2(), is(2));
        assertThat(event.get().getWhoScored(), is(2));
        assertThat(event.get().getPointsScored(), is(2));
    }

    @Test
    public void testConstructorWithNull(){
        Optional<MatchEvent> event = new MatchEvent(null).apply();
        assertThat(event.isPresent(), is(false));
    }

    @Test
    public void testConstructorWithEmptyString(){
        Optional<MatchEvent> event = new MatchEvent("").apply();
        assertThat(event.isPresent(), is(false));
    }

    @Test
    public void testConstructorWithErrors(){
        Optional<MatchEvent> event = new MatchEvent("f81016").apply();
        assertThat(event.isPresent(), is(false));
    }

}