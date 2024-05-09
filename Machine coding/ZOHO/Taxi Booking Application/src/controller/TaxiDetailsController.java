package controller;

import model.Taxi;
import model.Ticket;
import repository.TaxiManagementSystemRepository;
import service.UserInputOutputService;

import java.util.List;

public class TaxiDetailsController {
    public static void handleTaxiDetailsRequest(){
        List<Ticket>ticketList = TaxiManagementSystemRepository.getAllTickets();
        UserInputOutputService.printTaxiDetails(ticketList);
    }
}
