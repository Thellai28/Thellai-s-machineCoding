package controller;

import model.Passenger;
import repository.RailwayReservationSystemRepository;
import service.BookingService;
import service.UserInputOutputService;

public class BookingController {

    public static void handleBooking(){
        Passenger passenger = BookingService.getUserDetails();

        if(passenger.getAge() < 5 ){
            RailwayReservationSystemRepository.getChildList().add(passenger); // adding children blow 5 into list :

            String message = "🎉🎉🎉 No need to book ticket for children under age 5";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
            return;
        }

       if( passenger.getBerthPreference().equalsIgnoreCase("LOWER")){
           BookingService.bookLowerBerth(passenger);

       }else if( passenger.getBerthPreference().equalsIgnoreCase("UPPER") ||
               passenger.getBerthPreference().equalsIgnoreCase("MIDDLE") ){
           BookingService.bookUpperOrMiddleBerth(passenger);

       }else{
           BookingService.bookRacBerth(passenger);
       }
    }

}
