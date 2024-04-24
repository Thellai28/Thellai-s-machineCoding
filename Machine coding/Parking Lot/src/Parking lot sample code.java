import java.util.ArrayList;
import java.util.List;

class Vehicle {
    private String vehicleType;
    private String regNo;
    private String color;

    public Vehicle(String vehicleType, String regNo, String color) {
        this.vehicleType = vehicleType;
        this.regNo = regNo;
        this.color = color;
    }

    // Getters
    public String getVehicleType() {
        return vehicleType;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getColor() {
        return color;
    }
}

class Ticket {
    private String parkingLotId;
    private int floorNo;
    private int slotNo;

    public Ticket(String parkingLotId, int floorNo, int slotNo) {
        this.parkingLotId = parkingLotId;
        this.floorNo = floorNo;
        this.slotNo = slotNo;
    }

    // Getter
    public String getTicketId() {
        return parkingLotId + "_" + floorNo + "_" + slotNo;
    }
}

class ParkingSlot {
    private String slotType;
    private Vehicle vehicle;

    public ParkingSlot(String slotType) {
        this.slotType = slotType;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void unparkVehicle() {
        this.vehicle = null;
    }

    // Getter
    public Vehicle getVehicle() {
        return vehicle;
    }
}

class ParkingFloor {
    private int floorNo;
    private List<ParkingSlot> slots;

    public ParkingFloor(int floorNo, int numTruckSlots, int numBikeSlots, int numCarSlots) {
        this.floorNo = floorNo;
        this.slots = new ArrayList<>();
        for (int i = 0; i < numTruckSlots; i++) {
            slots.add(new ParkingSlot("TRUCK"));
        }
        for (int i = 0; i < numBikeSlots; i++) {
            slots.add(new ParkingSlot("BIKE"));
        }
        for (int i = 0; i < numCarSlots; i++) {
            slots.add(new ParkingSlot("CAR"));
        }
    }

    public ParkingSlot findAvailableSlot(String vehicleType) {
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable() && slot.getSlotType().equals(vehicleType)) {
                return slot;
            }
        }
        return null;
    }

    public int getFreeCount(String vehicleType) {
        int count = 0;
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable() && slot.getSlotType().equals(vehicleType)) {
                count++;
            }
        }
        return count;
    }

    public List<String> getFreeSlots(String vehicleType) {
        List<String> freeSlots = new ArrayList<>();
        for (int i = 0; i < slots.size(); i++) {
            ParkingSlot slot = slots.get(i);
            if (slot.isAvailable() && slot.getSlotType().equals(vehicleType)) {
                freeSlots.add(String.valueOf(i + 1));
            }
        }
        return freeSlots;
    }

    public List<String> getOccupiedSlots(String vehicleType) {
        List<String> occupiedSlots = new ArrayList<>();
        for (int i = 0; i < slots.size(); i++) {
            ParkingSlot slot = slots.get(i);
            if (!slot.isAvailable() && slot.getVehicle().getVehicleType().equals(vehicleType)) {
                occupiedSlots.add(String.valueOf(i + 1));
            }
        }
        return occupiedSlots;
    }

    // Getter
    public String getSlotType(int index) {
        return slots.get(index).getSlotType();
    }
}

class ParkingLot {
    private String parkingLotId;
    private List<ParkingFloor> floors;

    public ParkingLot(String parkingLotId, int numFloors, int numSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        this.floors = new ArrayList<>();
        for (int i = 1; i <= numFloors; i++) {
            floors.add(new ParkingFloor(i, 1, 2, numSlotsPerFloor - 3));
        }
    }

    public Ticket parkVehicle(String vehicleType, String regNo, String color) {
        for (ParkingFloor floor : floors) {
            ParkingSlot slot = floor.findAvailableSlot(vehicleType);
            if (slot != null) {
                Vehicle vehicle = new Vehicle(vehicleType, regNo, color);
                slot.parkVehicle(vehicle);
                return new Ticket(parkingLotId, floor.getFloorNo(), floors.indexOf(floor) + 1);
            }
        }
        return null;
    }

    public String[] unparkVehicle(String ticketId) {
        String[] parts = ticketId.split("_");
        int floorNo = Integer.parseInt(parts[1]);
        int slotNo = Integer.parseInt(parts[2]);
        if (floorNo >= 1 && floorNo <= floors.size() && slotNo >= 1
                && slotNo <= floors.get(floorNo - 1).getSlots().size()) {
            ParkingSlot slot = floors.get(floorNo - 1).getSlots().get(slotNo - 1);
            if (!slot.isAvailable()) {
                Vehicle vehicle = slot.getVehicle();
                slot.unparkVehicle();
                return new String[] { vehicle.getRegNo(), vehicle.getColor() };
            }
        }
        return null;
    }

    public void display(String displayType, String vehicleType) {
        if (displayType.equals("free_count")) {
            for (ParkingFloor floor : floors) {
                int count = floor.getFreeCount(vehicleType);
                System.out.println(
                        "No. of free slots for " + vehicleType + " on Floor " + floor.getFloorNo() + ": " + count);
            }
        } else if (displayType.equals("free_slots")) {
            for (ParkingFloor floor : floors) {
                List<String> freeSlots = floor.getFreeSlots(vehicleType);
                System.out.println("Free slots for " + vehicleType + " on Floor " + floor.getFloorNo() + ": "
                        + String.join(", ", freeSlots));
            }
        } else if (displayType.equals("occupied_slots")) {
            for (ParkingFloor floor : floors) {
                List<String> occupiedSlots = floor.getOccupiedSlots(vehicleType);
                System.out.println("Occupied slots for " + vehicleType + " on Floor " + floor.getFloorNo() + ": "
                        + String.join(", ", occupiedSlots));
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = null;
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].equals("create_parking_lot")) {
                String parkingLotId = command[1];
                int numFloors = Integer.parseInt(command[2]);
                int numSlotsPerFloor = Integer.parseInt(command[3]);
                parkingLot = new ParkingLot(parkingLotId, numFloors, numSlotsPerFloor);
                System.out.println("Created parking lot with " + numFloors + " floors and " + numSlotsPerFloor
                        + " slots per floor");
            } else if (command[0].equals("park_vehicle")) {
                Ticket ticket = parkingLot.parkVehicle(command[1], command[2], command[3]);
                if (ticket != null) {
                    System.out.println("Parked vehicle. Ticket ID: " + ticket.getTicketId());
                } else {
                    System.out.println("Parking Lot Full");
                }
            } else if (command[0].equals("unpark_vehicle")) {
                String[] result = parkingLot.unparkVehicle(command[1]);
                if (result != null) {
                    System.out.println(
                            "Unparked vehicle with Registration Number: " + result[0] + " and Color: " + result[1]);
                } else {
                    System.out.println("Invalid Ticket");
                }
            } else if (command[0].equals("display")) {
                parkingLot.display(command[1], command[2]);
            } else if (command[0].equals("exit")) {
               break;
            }
        }
        scanner.close();
    }
}
