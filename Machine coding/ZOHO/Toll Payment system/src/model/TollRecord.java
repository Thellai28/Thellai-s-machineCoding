package model;

public class TollRecord {
    private int tollID;
    private int vehicleNumber;
    private String vehicleType;
    private int amountPaid;

    public TollRecord( int amountPaid, int tollID, int vehicleNumber, String vehicleType ) {
        this.amountPaid = amountPaid;
        this.tollID = tollID;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return String.format("%-5d| %-15d| %-15s| %-5d|", tollID, vehicleNumber, vehicleType, amountPaid);
    }
}
