package model;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private static int idGenerator = 1111;

    private int vehicleNumber;
    private String vehicleType;
    private List<Ticket> ticketList;
    private int startingPoint;
    private int destinationPoint;
    private String ownerStatus; // normal or VIP

    public Vehicle( int destinationPoint, String ownerStatus,
                    int startingPoint, String vehicleType ) {
        this.destinationPoint = destinationPoint;
        this.ownerStatus = ownerStatus;
        this.startingPoint = startingPoint;
        this.vehicleType = vehicleType;
        this.vehicleNumber = idGenerator++;
        ticketList = new ArrayList<>();
    }

    //--------------------< Getter & setter >-------------------------------

    public int getDestinationPoint() {
        return destinationPoint;
    }

    public String getOwnerStatus() {
        return ownerStatus;
    }

    public int getStartingPoint() {
        return startingPoint;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

}
