package controller;

import model.Ticket;
import repository.RailwayReservationSystemRepository;
import service.UserInputOutputService;

import java.util.List;

public class TicketPrintingController {
    public static void handleTicketPrintingRequest(){
        List<Ticket> ticketList = getAllBookedTickets();
        UserInputOutputService.printAllTickets(ticketList);
    }

    private static List<Ticket> getAllBookedTickets(){
        return RailwayReservationSystemRepository.getAllBookedTickets();
    }
}
