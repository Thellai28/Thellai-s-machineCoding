package model;

public class Ticket {
    private static int idGenerator = 1;

    private int ticketId;
    private int vehicleNumber;
    private int from;
    private int to;
    private int tollBoothId;
    private int amountPaid;

    public Ticket( int amountPaid, TollBooth tollBooth,  Vehicle vehicle ) {
        this.vehicleNumber = vehicle.getVehicleNumber();
        this.from = vehicle.getStartingPoint();
        this.to = vehicle.getDestinationPoint();
        this.tollBoothId = tollBooth.getTollBoothId();
        this.amountPaid = amountPaid;
        this.ticketId = idGenerator++;

    }

    @Override
    public String toString() {
        return String.format("%-15d| %-7d| %-5d| %-3d| %-5d", vehicleNumber, tollBoothId, from, to, amountPaid);
    }

    public String getTollAndPaymentDetails(){
        return String.format("%-5d| %-5d| ", tollBoothId,amountPaid );
    }

}
