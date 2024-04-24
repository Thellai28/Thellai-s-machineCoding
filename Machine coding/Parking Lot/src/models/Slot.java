package models;

public class Slot {
    private int slotNumber;
    private int floorNo;
    private String slotType;
    private Vehicle vehicle;

    public Slot( int number, String slotType, int floorNo ) {
        this.floorNo = floorNo;
        this.slotNumber = number;
        this.slotType = slotType;
    }

    public int getSlotNumber() {
        return slotNumber;
    }



    public String getSlotType() {
        return slotType;
    }



    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isVehicleParked(){
        return vehicle == null;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setVehicle( Vehicle vehicle ) {
        this.vehicle = vehicle;
    }
}
