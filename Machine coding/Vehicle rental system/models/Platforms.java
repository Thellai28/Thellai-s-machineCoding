package models;

import java.util.ArrayList;
import java.util.List;

public class Platforms {
    private List<Vehicle> vehicles;
    private int StationNumber;

    public Platforms( int stationNumber) {
        StationNumber = stationNumber;
        this.vehicles = new ArrayList<>();
    }

    public int getStationNumber() {
        return StationNumber;
    }

    public void setStationNumber( int stationNumber ) {
        StationNumber = stationNumber;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles( List<Vehicle> vehicles ) {
        this.vehicles = vehicles;
    }

    public void addVehichle( Vehicle vehicle ){
        vehicles.add(vehicle);
    }

    //---------------------------------------------------------------------

}
