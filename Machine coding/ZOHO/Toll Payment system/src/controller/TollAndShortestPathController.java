package controller;

import model.*;
import repository.TollPaymentSystemRepository;
import service.UserInputOutputService;

public class TollAndShortestPathController {
    public static void manageJourney(){
        final int TOTAL_NUMBER_OF_STATIONS = TollPaymentSystem.TOTAL_STATIONS;
        Vehicle vehicle = createVehicle();
        String direction = getDirectionOfJourney(vehicle, TOTAL_NUMBER_OF_STATIONS);

        int tollAmount = 0;
        if( direction.equalsIgnoreCase("CLOCK_WISE") ){
            tollAmount = startJourneyInClockWise(TOTAL_NUMBER_OF_STATIONS, vehicle );
        }else{
            tollAmount = startJourneyInAntiClockWise(TOTAL_NUMBER_OF_STATIONS, vehicle);
        }
        UserInputOutputService.printMessageAndOneLine("🎊🎊🎊Vehicle travelled from "
                + vehicle.getStartingPoint() +" to "
                + vehicle.getDestinationPoint() + ". And the total toll amount is "
                + tollAmount);
        UserInputOutputService.printTollAndPayments(vehicle.getTicketList());
    }

    private static Vehicle createVehicle(){

        String vehicleType = UserInputOutputService.getVehicleType();
        String ownerStatus = "NORMAL";
        if( vehicleType.equalsIgnoreCase("Car")){
            int response = UserInputOutputService.getVipStatus();
            ownerStatus = ( response == 1 )? "VIP" : ownerStatus;
        }

        int startingPoint = UserInputOutputService.getStartingDestination();
        int destinationPoint = UserInputOutputService.getDestinationPoint();

        Vehicle vehicle = new Vehicle(destinationPoint, ownerStatus,
                startingPoint, vehicleType);

        TollPaymentSystemRepository.addVehicleIntoRepository(vehicle);

        return vehicle;
    }

    private static String getDirectionOfJourney( Vehicle vehicle, int totalNumberOfStations ){

        int clockWiseCount = getClockWiseStations(vehicle, totalNumberOfStations);
        int antiClockWiseCount = getAntiClockWiseStations(vehicle, totalNumberOfStations);

        return ( clockWiseCount < antiClockWiseCount ) ? "CLOCK_WISE" : "ANTI_CLOCK_WISE";
    }

    private static int getClockWiseStations( Vehicle vehicle,
                                             int totalNumberOfStations ){
        int clockWiseStations = 1;
        int currLocation = vehicle.getStartingPoint();

        while( currLocation != vehicle.getDestinationPoint() ){
            clockWiseStations++;
            currLocation++;
            if( currLocation > totalNumberOfStations ){
                currLocation = 1;
            }
        }
        return clockWiseStations;
    }


    private static int getAntiClockWiseStations( Vehicle vehicle,
                                                 int totalNumberOfStations ){
        int antiClockWiseStations = 1;
        int currLocations = vehicle.getStartingPoint();

        while( currLocations != vehicle.getDestinationPoint() ){
            antiClockWiseStations++;
            currLocations--;
            if( currLocations < 1 ){
                currLocations = totalNumberOfStations;
            }
        }
        return antiClockWiseStations;
    }


    private static int startJourneyInClockWise(  int totalNumberOfStations,
                                                  Vehicle vehicle){
       int currentLocation = vehicle.getStartingPoint();
       int totalTollAmount = 0;

       while( currentLocation != vehicle.getDestinationPoint() ){
           totalTollAmount += checkTollAndCalculateAmount(currentLocation, vehicle);

           currentLocation++;
           if(currentLocation > totalNumberOfStations ){
               currentLocation = 1;
           }
       }

       totalTollAmount += checkTollAndCalculateAmount(vehicle.getDestinationPoint(), vehicle); //what if the destination has tollBooth
        return totalTollAmount;
    }

    private static  int startJourneyInAntiClockWise( int totalNumberOfStations,
                                                     Vehicle vehicle){
        int currentLocation = vehicle.getStartingPoint();
        int totalTollAmount = 0;

        while( currentLocation != vehicle.getDestinationPoint() ){
            totalTollAmount += checkTollAndCalculateAmount(currentLocation, vehicle);

            currentLocation--;
            if( currentLocation < 1 ){
                currentLocation = totalNumberOfStations;
            }
        }
        totalTollAmount += checkTollAndCalculateAmount(vehicle.getDestinationPoint(), vehicle); // what if the destination has a toll ?
        return totalTollAmount;
    }


    private static int checkTollAndCalculateAmount( int location, Vehicle vehicle){
        int tollAmount = 0;
        TollBooth currLocationTollBooth = TollPaymentSystemRepository
                .getTollBoothAtThisLocation(location);

        if(currLocationTollBooth != null ){
            int amountPayable = calculateTollBoothFee(vehicle, currLocationTollBooth);
            Ticket ticket = createTicket(vehicle, currLocationTollBooth, amountPayable);
            storeTicketInVehicle(vehicle, ticket);
            createAndStoreTollRecord(amountPayable, currLocationTollBooth, vehicle);

            tollAmount += amountPayable;
        }
        return tollAmount;
    }

    private static void createAndStoreTollRecord( int amountPaid, TollBooth tollBooth, Vehicle vehicle ) {
        TollRecord tollRecord = new TollRecord(amountPaid, tollBooth.getTollBoothId(),
                vehicle.getVehicleNumber(), vehicle.getVehicleType());
        tollBooth.getTollRecordList().add(tollRecord);
    }

    private static int calculateTollBoothFee( Vehicle vehicle, TollBooth tollBooth ){
        int feeInTollBooth = tollBooth.getVehicleAndFeeMap().get(vehicle.getVehicleType());

        int discountPercentage = ( vehicle.getOwnerStatus().equals("VIP") )
                ? tollBooth.getDiscountForVip() : 0;
        int discountAmount = 0;

        if( discountPercentage > 0 ){
            discountAmount = (feeInTollBooth * discountPercentage) / 100;
        }

        int amountPayable = feeInTollBooth - discountAmount;
        return amountPayable;
    }

    private static Ticket createTicket( Vehicle vehicle, TollBooth tollBooth, int amountPaid ){
        return new Ticket(amountPaid, tollBooth, vehicle);
    }

    private static void storeTicketInVehicle( Vehicle vehicle, Ticket ticket ){
        vehicle.getTicketList().add(ticket);
    }
}
