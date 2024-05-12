package model;

public class Ticket {
    private static int idGenerator = 1;
    private int ticketNumber;
    private Seat bookedSeat;
    private User bookedUser;

    public Ticket( User bookedUser, Seat bookedSeat ) {
        this.bookedUser = bookedUser;
        this.bookedSeat = bookedSeat;
        this.ticketNumber = idGenerator++;
    }

    //--------------< Getter & Setters >------------------------------


    public User getBookedUser() {
        return bookedUser;
    }

    public void setBookedPerson( User bookedUser ) {
        this.bookedUser = bookedUser;
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
