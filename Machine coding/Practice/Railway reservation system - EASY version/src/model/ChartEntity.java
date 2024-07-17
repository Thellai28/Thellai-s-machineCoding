package model;

public class ChartEntity {
    private static int globalIdGenerator = 1;

    private int id;
    private String name;
    private String seatNumber;
    private String seatType;

    public ChartEntity( Ticket ticket ) {
        this.id = globalIdGenerator++;
        this.name = ticket.getPassenger().getName();
        this.seatNumber = ticket.getSeat().getSeatNumber();
        this.seatType = ticket.getSeat().getType();
    }

    @Override
    public String toString() {
        return String.format("%-5d| %-20s| %-12s| %-7s|", id, name, seatNumber, seatType);
    }

}
