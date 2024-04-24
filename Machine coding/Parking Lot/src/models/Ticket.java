package models;

public class Ticket {
    private static int ticketIdCounter = 0;
    private int ticketId;
    private int floorNumber;
    private Slot parkedSlot;

    public Ticket( Slot parkedSlot ) {
        ticketIdCounter++;
        this.ticketId = ticketIdCounter;
        this.parkedSlot = parkedSlot;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Slot getParkedSlot() {
        return parkedSlot;
    }

    public int getTicketId() {
        return ticketId;
    }
}
