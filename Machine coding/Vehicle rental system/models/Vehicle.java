package models;

public class Vehicle {
    private int platformNumber;
    private String vehicleType;
    private String number;
    private BookingStatusEnum BookingStatus;
    private double rentPerHour;


    public int getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber( int platformNumber ) {
        this.platformNumber = platformNumber;
    }

    public Vehicle( String number, double rentPerHour,
                    BookingStatusEnum status, String vehicleType, int stationNumber ) {
        this.platformNumber = stationNumber;
        this.number = number;
        this.rentPerHour = rentPerHour;
        this.BookingStatus = status;
        this.vehicleType = vehicleType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber( String number ) {
        this.number = number;
    }

    public double getRentPerHour() {
        return rentPerHour;
    }

    public void setRentPerHour( double rentPerHour ) {
        this.rentPerHour = rentPerHour;
    }

    public BookingStatusEnum getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus( BookingStatusEnum bookingStatus ) {
        this.BookingStatus = bookingStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType( String vehicleType ) {
        this.vehicleType = vehicleType;
    }
}
