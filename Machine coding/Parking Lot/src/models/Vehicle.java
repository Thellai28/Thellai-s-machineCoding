package models;

public class Vehicle {
    private String number;
    private String vehicleType;
    private String color;

    public Vehicle( String color, String number, String vehicleType ) {        this.color = color;
        this.number = number;
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor( String color ) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber( String number ) {
        this.number = number;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType( String vehicleType ) {
        this.vehicleType = vehicleType;
    }

}
