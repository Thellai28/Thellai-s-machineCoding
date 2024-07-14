package model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private static int pnrNumberGenerator = 1;

    private int pnr;
    private char source;
    private char destination;
    private List<Integer>seatsBooked;

    public Ticket( char source, char destination ) {
        this.destination = destination;
        this.source = source;
        this.pnr = pnrNumberGenerator++;
        seatsBooked = new ArrayList<>();
    }

    public char getDestination() {
        return destination;
    }

    public void setDestination( char destination ) {
        this.destination = destination;
    }

    public int getPnr() {
        return pnr;
    }

    public void setPnr( int pnr ) {
        this.pnr = pnr;
    }

    public List<Integer> getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked( List<Integer> seatsBooked ) {
        this.seatsBooked = seatsBooked;
    }

    public char getSource() {
        return source;
    }

    public void setSource( char source ) {
        this.source = source;
    }

    public static int getPnrNumberGenerator() {
        return pnrNumberGenerator;
    }

    public static void setPnrNumberGenerator( int pnrNumberGenerator ) {
        Ticket.pnrNumberGenerator = pnrNumberGenerator;
    }


}
