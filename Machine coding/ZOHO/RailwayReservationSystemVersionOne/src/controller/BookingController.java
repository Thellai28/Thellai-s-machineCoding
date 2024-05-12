package controller;

import model.User;
import repository.RailwayReservationSystemRepository;
import service.BookingService;


public class BookingController {

    public static void handleBooking(){
        User user = BookingService.getUserDetails();

        if(user.getAge() < 5 ){
            RailwayReservationSystemRepository.getChildList().add(user); // adding children blow 5 into list :
            System.out.println("🎉🎉🎉 No need to book ticket for children under age 5");
            return;
        }

       if( user.getBerthPreference().equalsIgnoreCase("LOWER")){
           BookingService.bookLowerBerth(user);

       }else if( user.getBerthPreference().equalsIgnoreCase("UPPER") ||
               user.getBerthPreference().equalsIgnoreCase("MIDDLE") ){
           BookingService.bookUpperOrMiddleBerth(user);

       }else{
           BookingService.bookRacBerth(user);
       }
    }

}
