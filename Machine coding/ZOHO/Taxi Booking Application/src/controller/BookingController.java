package controller;

import model.Taxi;
import model.Ticket;
import service.BookingService;
import service.UserInputOutputService;


import java.util.List;

public class BookingController {
    public static void handleBooking(){
        int customerId = UserInputOutputService.getCustomerIdFromUser();
        char pickUpPoint = UserInputOutputService.getPickupPointFromUser();
        char destinationPoint = UserInputOutputService.getDestinationPointFromUser();
        int pickUpTime = UserInputOutputService.getPickUpTimeFromUser();

        List<Taxi> taxiList = BookingService.getAvailableTaxiList();
        List<Taxi> eligibleTaxiList = BookingService
                                        .chooseEligibleTaxisFromTaxiList(taxiList, pickUpTime);

        if( eligibleTaxiList.size() == 0 ){
            System.out.println("Sorry, no taxi Available");
        }else{
            List<Taxi> sortedList = BookingService
                                    .sortTaxiListBasedOnDistance( eligibleTaxiList, pickUpPoint );
            Taxi chosenTaxi = sortedList.get(0);


            BookingService.bookTaxi(chosenTaxi, pickUpPoint, destinationPoint, pickUpTime);

            Ticket ticket = BookingService.createTicket(destinationPoint, chosenTaxi,
                    pickUpPoint, pickUpTime, customerId);

            BookingService.addTicketIntoTicketMap(ticket);

            System.out.println("🎉🎉🎉 Taxi number " + chosenTaxi.getTaxiNumber() +
                    " is booked and your ticket number is " + ticket.getTicketNumber() +
                    ". Have a nice Day 😄");

            UserInputOutputService.printTicket(ticket);
        }
    }
}
