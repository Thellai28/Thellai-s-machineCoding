package services;


import models.Slot;
import models.Ticket;
import models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static services.DisplayInConsoleService.*;
import static controllers.ParkingLotController.*;

public class ParkingLotService {
    private List<ParkingFloorService> floors;
    private Map<Integer, Ticket> parkedVehiclesMap;

    public ParkingLotService(int floorAllowed, int carSlotsCount,
                             int bikeSlotsCount, int busSlotsCount) {
        this.floors = new ArrayList<>();
        this.parkedVehiclesMap = new HashMap<>();

        for( int i = 1; i <= floorAllowed; i++ ){
            //System.out.println("Creating floor " + i);
            floors.add( new ParkingFloorService(i, bikeSlotsCount,
                    carSlotsCount, busSlotsCount));
        }
    }

    public int parkVehicle(){
        Vehicle vehicle = getVehicleDetails();
        Slot availableSlot = null;
        for( ParkingFloorService currFloor : floors ){
            availableSlot = currFloor.getAvailableSlot(vehicle.getVehicleType());
            if( availableSlot != null ){
                break;
            }
        }

        if( availableSlot == null ){
            return -1;
        }
        availableSlot.setVehicle(vehicle);
        Ticket ticket = new Ticket(availableSlot);
        parkedVehiclesMap.put(ticket.getTicketId(), ticket);
        return ticket.getTicketId();
    }

    public void unParkVehicle(  ){
        int ticketNumber = getTicketNumber();
        if( !parkedVehiclesMap.containsKey(ticketNumber) ){
            System.out.println("Invalid ticket number, please enter a valid number ");
            unParkVehicle();
        }

        Ticket ticket = parkedVehiclesMap.get(ticketNumber);
        ticket.getParkedSlot().setVehicle(null);
        parkedVehiclesMap.remove(ticketNumber);
        System.out.println("UnParked Your vehicle. Thank you, visit again");
    }

    public void getAllAvailableSlots(){
        for( ParkingFloorService floor : floors ){
            int carSlots = floor.getAvailableSlotCount("CAR");
            int bikeSlots = floor.getAvailableSlotCount("BIKE");
            int busSlots = floor.getAvailableSlotCount("BUS");

            printAvailableSlots(floor.getFloorNo(), carSlots, "CAR");
            printAvailableSlots(floor.getFloorNo(), bikeSlots, "BIKE");
            printAvailableSlots(floor.getFloorNo(), busSlots, "BUS");

            printHorizontalLine();
        }
    }

    public void shutDownParkingLot(){
        isParkingLotActive = false;
    }

    public void handleIncomingRequest( ){
        int requestedService = displayChoicesAndGetResponse();
        switch (requestedService){
            case 1 : {
                int ticketNumber = parkVehicle();
                System.out.println("The ticket number is " + ticketNumber);
                if( ticketNumber == -1 ){
                    System.out.println("Sorry, Parking lot is full, please come after sometime. Have a nice day :)");
                    break;
                }
                System.out.println("Your vehicle is successfully parked. Your ticket number is " + ticketNumber);
                break;
            } case 2 : {
                unParkVehicle();
                break;
            } case 3 : {
                getAllAvailableSlots();
                break;
            } case 4 : {

            } case 5 : {
                shutDownParkingLot();
                closeScanner();
                break;
            } default:{
                System.out.println("Invalid Response, please provide a valid response");
            }
        }
    }


}
