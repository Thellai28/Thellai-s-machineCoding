package model;

public class Ticket {
    private static int idGenerator = 1;
    private int ticketNumber;
    private Seat bookedSeat;
    private Passenger bookedPassenger;

    public Ticket( Passenger bookedPassenger, Seat bookedSeat ) {
        this.bookedPassenger = bookedPassenger;
        this.bookedSeat = bookedSeat;
        this.ticketNumber = idGenerator++;
    }

    //--------------< Getter & Setters >------------------------------


    public Passenger getBookedUser() {
        return bookedPassenger;
    }

    public void setBookedPerson( Passenger bookedPassenger ) {
        this.bookedPassenger = bookedPassenger;
    }

    public Seat getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat( Seat bookedSeat ) {
        this.bookedSeat = bookedSeat;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator( int idGenerator ) {
        Ticket.idGenerator = idGenerator;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber( int ticketNumber ) {
        this.ticketNumber = ticketNumber;
    }
}
