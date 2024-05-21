package controller;

import repository.TollPaymentSystemRepository;
import service.UserInputOutputService;

public class VehicleTicketsController {
    public static void printAllTickets(){
        UserInputOutputService
                .printVehicleTickets(TollPaymentSystemRepository
                        .getAllVehicles());
    }
}
