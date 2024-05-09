package model;

public class Ticket {
    private static int ticketId = 1;
    private int ticketNumber;
    private Taxi taxi;
    private int customerId;
    private char pickUpPoint;
    private char destinationPoint;
    private int pickUpTime;
    private int dropTime;
    private int travelCharges;

    public Ticket( char destinationPoint, int dropTime,
                   char pickUpPoint, int pickUpTime, Taxi taxi,
                   int travelCharges, int customerId ) {
        this.ticketNumber = ticketId++;
        this.destinationPoint = destinationPoint;
        this.dropTime = dropTime;
        this.pickUpPoint = pickUpPoint;
        this.pickUpTime = pickUpTime;
        this.taxi = taxi;
        this.travelCharges = travelCharges;
        this.customerId = customerId;
    }

    //---------------< Getters & setters >--------------------------------

    public char getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint( char destinationPoint ) {
        this.destinationPoint = destinationPoint;
    }

    public int getDropTime() {
        return dropTime;
    }

    public void setDropTime( int dropTime ) {
        this.dropTime = dropTime;
    }

    public char getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint( char pickUpPoint ) {
        this.pickUpPoint = pickUpPoint;
    }

    public int getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime( int pickUpTime ) {
        this.pickUpTime = pickUpTime;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi( Taxi taxi ) {
        this.taxi = taxi;
    }

    public static int getTicketId() {
        return ticketId;
    }

    public static void setTicketId( int ticketId ) {
        Ticket.ticketId = ticketId;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber( int ticketNumber ) {
        this.ticketNumber = ticketNumber;
    }

    public int getTravelCharges() {
        return travelCharges;
    }

    public void setTravelCharges( int travelCharges ) {
        this.travelCharges = travelCharges;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId( int customerId ) {
        this.customerId = customerId;
    }
}
