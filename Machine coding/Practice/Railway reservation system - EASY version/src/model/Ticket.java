package model;

public class Ticket {
    private static int globalTicketIdGenerator = 1;

    public int ticketId;
    private Passenger passenger;
    private Seat seat;
    private String status;

    public Ticket( Passenger passenger, Seat seat ) {
        this.ticketId = globalTicketIdGenerator++;
        this.passenger = passenger;
        this.seat = seat;
        this.status = "VALID";
    }

    // ------------------< Getters & Setters >------------------------------

    public Passenger getPassenger() {
        return passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
