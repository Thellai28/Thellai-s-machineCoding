package controller;

import model.RailwayReservationSystem;
import model.Ticket;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

public class TicketSummaryHandler {
    public static void handleTicketSummary(){
        int ticketNumber = UserInputOutputService.getTicketId();
        Ticket ticket = RailwayReservationRepository
                .railwayReservationSystem
                .getTicketMap().get(ticketNumber);

        if( ticket != null ){
            UserInputOutputService.printTicketSummary(ticket);
        }else{
            UserInputOutputService.printMessageAndOneLine("❌-- invalid Ticket id");
        }
    }
}
