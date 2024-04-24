package services;

import models.Slot;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloorService {
    private int floorNo;
    private List<Slot> slots;

    public ParkingFloorService( int floorNo, int bikeSlotsAllocated,
                                int carSlotsAllocated, int busSlotsAllocated ) {
        this.floorNo = floorNo;
        slots = new ArrayList<>();

        for( int i = 0; i < bikeSlotsAllocated; i++ ){
            slots.add(new Slot(i, "BIKE", floorNo));
        }
        //System.out.println("Created " + bikeSlotsAllocated + " bike slots");

        for( int i = 0; i < carSlotsAllocated; i++ ){
            slots.add(new Slot(i, "CAR", floorNo));
        }
        //System.out.println("Created " + carSlotsAllocated + " car slots");

        for( int i = 0; i < busSlotsAllocated; i++ ){
            slots.add(new Slot(i, "BUS", floorNo));
        }
        //System.out.println("Created " + busSlotsAllocated + " bus slots");
    }

    public Slot getAvailableSlot( String vehicleType ){
        System.out.println("the slot size is " + slots.size());
        for( Slot currSlot : slots ){
            System.out.println("The slot type " + currSlot.getSlotType());
            System.out.println("Is slot filled : " + (currSlot.getVehicle()== null));
            if( currSlot.getVehicle()== null && currSlot.getSlotType().equals(vehicleType) ){
                return currSlot;
            }
        }
        return null;
    }

    public int getAvailableSlotCount( String vehicleType ){
        int count = 0;
        for( Slot currSlot : slots ){
            if( currSlot.getVehicle() == null && currSlot.getSlotType().equals(vehicleType)){
                count++;
            }
        }
        return count;
    }

    public int getOccupiedSlotsCount(){
        int count = 0;
        for( Slot currSlot : slots ){
            if( !currSlot.isVehicleParked() ){
                count++;
            }
        }
        return count;
    }

    public int getFloorNo() {
        return floorNo;
    }
}
