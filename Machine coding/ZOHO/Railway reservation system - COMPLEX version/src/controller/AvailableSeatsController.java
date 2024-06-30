package controller;

import model.Seat;
import repository.RailwayReservationSystemRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class AvailableSeatsController {

    public static void handleAvailableSeatPrinting(){
        List<Seat> availableSeats = getAllAvailableSeats();
        UserInputOutputService.printAllAvailableSeats(availableSeats);
    }

    private static List<Seat> getAllAvailableSeats(){
        List<Seat> allSeatList = RailwayReservationSystemRepository.getAllSeats();
        List<Seat> availableSeats = new ArrayList<>();

        for( Seat seat : allSeatList ){
            if(seat.getUserList().size() < seat.getSeatCapacity() ){
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }
}
