package controller;

import model.Seat;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatAvailabilityController {
    public static void displaySeats() {
        String seatType = getSeatPreference();
        List<Seat> availableSeats = generatAvailableSeatList(
                RailwayReservationRepository.getSeatList(seatType)
        );

        if( availableSeats.isEmpty() ){
            UserInputOutputService
                    .printMessageAndSingleLine("⚠️---Sorry there is no available seats to display---⚠️");
        }else {
            UserInputOutputService.displaySeats(availableSeats);
        }
    }

    public static Seat displaySeatsAndChoose( String seatType ){
        List<Seat> availableSeats = generatAvailableSeatList(
                RailwayReservationRepository.getSeatList(seatType)
        );

        if( availableSeats.isEmpty() ){
            return null;
        }

        int idx = UserInputOutputService.displaySeatsAndChoose(availableSeats);
        return availableSeats.get(idx);
    }


    public static String getSeatPreference(){
        List<String> seatTypesList = Arrays.asList("AC", "NON-AC", "SEATER");
        int seatTypeIdx = UserInputOutputService.displaySeatTypes(seatTypesList);
        return seatTypesList.get(seatTypeIdx);
    }


    private static List<Seat> generatAvailableSeatList( List<Seat> seatList ){
        List<Seat> availableSeatList = new ArrayList<>();

        for( Seat seat : seatList ){
            if( seat.getStatus().equals("AVAILABLE")){
                availableSeatList.add(seat);
            }
        }
        return availableSeatList;
    }
}
