package repository;

import model.TollBooth;
import model.TollPaymentSystem;
import model.Vehicle;

import java.util.List;
import java.util.Map;

public class TollPaymentSystemRepository {
    private static TollPaymentSystem tollPaymentSystem;

    public static void initializeTollPaymentRepository(){
        tollPaymentSystem = new TollPaymentSystem();
    }

    public static void addVehicleIntoRepository( Vehicle vehicle ){
        tollPaymentSystem.getVehicleList().add(vehicle);
    }

    public static TollBooth getTollBoothAtThisLocation( int location ){
        return tollPaymentSystem.getTollBoothMap().get(location);
    }

    public static Map<Integer, TollBooth> getAllTolls(){
        return tollPaymentSystem.getTollBoothMap();
    }

    public static List<Vehicle> getAllVehicles(){
        return tollPaymentSystem.getVehicleList();
    }

}
