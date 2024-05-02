package models;

import services.UserIoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalSystem {
    private List<Platforms> platformsList;
    private Map<Integer, Ticket> bookedVehicles;


    public RentalSystem(  ) {
        this.platformsList = new ArrayList<>();
        this.bookedVehicles = new HashMap<>();

        System.out.println();
        System.out.println();
        System.out.println("Initializing rental system...");
        UserIoService.printHorizontalLines();

        final int SUV_COUNT = 3;
        final int SEDAN_COUNT = 4;
        final int HATCHBACK_COUNT = 3;
        final int STATION_COUNT = 3;

        for( int i = 0; i < STATION_COUNT; i++ ){
            Platforms platforms = new Platforms(i+1);

            // Pre-populating SUV's
            for( int suv = 0; suv < SUV_COUNT; suv++ ){
                Vehicle suvVehicle = new Vehicle( "SU" + suv +"_ABC" + 1234, 150.50,
                        BookingStatusEnum.AVAILABLE, "SUV", i+1);
                platforms.addVehichle(suvVehicle);
            }

            // Pre-populations Sedan's
            for( int seda = 0; seda < SEDAN_COUNT; seda++ ){
                Vehicle sedanVehicle = new Vehicle("SEDA" + seda +"_DEF" + 1234+"", 177.50,
                        BookingStatusEnum.AVAILABLE, "SEDAN", i+1);
                platforms.addVehichle(sedanVehicle);
            }

            // pre-populating hatchback :
            for( int hatch = 0; hatch < HATCHBACK_COUNT; hatch++ ){
                Vehicle hatchbackVehicle = new Vehicle("HATCH" + hatch +"_GHI" + 1234+"", 220.70,
                        BookingStatusEnum.AVAILABLE, "HATCHBACK", i+1);
                platforms.addVehichle(hatchbackVehicle);
            }
            this.platformsList.add(platforms);
        }
    }

    public Map<Integer, Ticket> getBookedVehicles() {
        return bookedVehicles;
    }

    public void setBookedVehicles( Map<Integer, Ticket> bookedVehicles ) {
        this.bookedVehicles = bookedVehicles;
    }

    public List<Platforms> getPlatformsList() {
        return platformsList;
    }

    public void setPlatformsList( List<Platforms> platformsList ) {
        this.platformsList = platformsList;
    }
}
