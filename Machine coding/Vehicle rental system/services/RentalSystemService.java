package services;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class RentalSystemService {
    private static RentalSystem rentalSystem;


    public static void initializeRentalSystemService(){
        rentalSystem = new RentalSystem();
    }

    public static List<Vehicle> allAvailableVehicles( String vehicleType ){
        List<Vehicle> AvailableVehicleList = new ArrayList<>();

        for( Platforms platforms : rentalSystem.getPlatformsList()){
            for( Vehicle vehicle : platforms.getVehicles() ){
                if( vehicle.getVehicleType().equalsIgnoreCase(vehicleType)
                        && vehicle.getBookingStatus().equals(BookingStatusEnum.AVAILABLE)){
                    AvailableVehicleList.add(vehicle);
                }
            }
        }
        return AvailableVehicleList;
    }

    public static void bookVehicle( Vehicle vehicle ){
        vehicle.setBookingStatus( BookingStatusEnum.BOOKED);
    }

    public static void dropVehicle( Vehicle vehicle ){
        vehicle.setBookingStatus( BookingStatusEnum.AVAILABLE);
    }

    public static void saveTicketsIntoMap( int ticketId, Ticket ticket ){
        rentalSystem.getBookedVehicles().put(ticketId, ticket);
    }

    public static Ticket fetchTicketFromMap( int ticketId ){
        return rentalSystem.getBookedVehicles().get(ticketId);
    }

}
