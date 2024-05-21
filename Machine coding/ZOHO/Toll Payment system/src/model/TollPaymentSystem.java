package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TollPaymentSystem {
    public static final int TOTAL_TOLLS =  4;
    public static final int TOTAL_STATIONS = 10;
    Map<Integer, TollBooth> tollBoothMap;
    List<Vehicle>vehicleList;

    public TollPaymentSystem() {
        this.tollBoothMap = new HashMap<>();
        this.vehicleList = new ArrayList<>();
        createTollBooths();
    }

    private void createTollBooths(){
        String[] vehicles = {"Car","Bus", "HeavyVehicles"};
        int[] fee = {20, 40, 60};

        for( int i = 0; i < TOTAL_TOLLS; i++ ){
            TollBooth tollBooth = new TollBooth();

            for( int j = 0; j < fee.length; j++ ){
                tollBooth.getVehicleAndFeeMap().put(vehicles[j], fee[j]);
            }
            tollBoothMap.put(tollBooth.getTollBoothId(), tollBooth);
        }
    }

    //------------< Getter & Setter >------------------------------

    public Map<Integer, TollBooth> getTollBoothMap() {
        return tollBoothMap;
    }

    public void setTollBoothMap( Map<Integer, TollBooth> tollBoothMap ) {
        this.tollBoothMap = tollBoothMap;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList( List<Vehicle> vehicleList ) {
        this.vehicleList = vehicleList;
    }
}
