package models;

import java.util.Date;

public class Ticket {
    public static int uniqueIdGenerator = 0;

    private int ticketId;
    private Vehicle vehicle;
    private String currentOwner;
    private Date checkInTime;

    public Ticket( String currentOwner, Vehicle vehicle ) {
        this. ticketId = uniqueIdGenerator++;
        this.currentOwner = currentOwner;
        this.vehicle = vehicle;
        this.checkInTime = new Date();
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime( Date checkInTime ) {
        this.checkInTime = checkInTime;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner( String currentOwner ) {
        this.currentOwner = currentOwner;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId( int ticketId ) {
        this.ticketId = ticketId;
    }

    public static int getUniqueIdGenerator() {
        return uniqueIdGenerator;
    }

    public static void setUniqueIdGenerator( int uniqueIdGenerator ) {
        Ticket.uniqueIdGenerator = uniqueIdGenerator;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle( Vehicle vehicle ) {
        this.vehicle = vehicle;
    }
}
