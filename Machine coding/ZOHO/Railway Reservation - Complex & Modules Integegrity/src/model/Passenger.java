package model;

public class Passenger {
    private char source;
    private char destination;
    private int ticketsRequired;
    private int id;
    static int idGenerator = 1;

    public Passenger( char destination, char source, int ticketsRequired ) {
        this.id = idGenerator++;
        this.destination = destination;
        this.source = source;
        this.ticketsRequired = ticketsRequired;
    }

    public char getDestination() {
        return destination;
    }

    public void setDestination( char destination ) {
        this.destination = destination;
    }

    public char getSource() {
        return source;
    }

    public void setSource( char source ) {
        this.source = source;
    }

    public int getTicketsRequired() {
        return ticketsRequired;
    }

    public void setTicketsRequired( int ticketsRequired ) {
        this.ticketsRequired = ticketsRequired;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }
}
