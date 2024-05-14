package controller;


import model.Ticket;

import service.CancelBookingService;
import service.UserInputOutputService;



public class CancelBookingController {

    public static void handleBookingCancel(){
        int ticketNumber = UserInputOutputService.getTicketNumber();
        Ticket ticket = CancelBookingService.retrieveTicketFromRepository(ticketNumber);
        CancelBookingService.removeUserFromSeat(ticket.getBookedSeat(), ticket.getBookedUser());

        String message = "🎉🎉🎉Hi " + ticket.getBookedUser().getName() + " Your ticket is successfully cancelled";
        UserInputOutputService.printMessageAndAddOneBlankLine(message);

        if( !ticket.getBookedSeat().getBerth().equalsIgnoreCase("RAC") ){
            // If a confirmed Passenger cancels seat, then a RAC user will move to confirmed seat:
            CancelBookingService.moveOneUserFromRacToConfirmation(ticket.getBookedSeat());
        }else{
            // If a RAC user cancels seat, we directly move one person from Waiting list to RAC
            CancelBookingService.moveOneUserFromWaitingListToRac();
        }

    }
}
