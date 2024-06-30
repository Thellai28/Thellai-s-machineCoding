package controller;

import model.Passenger;
import model.Seat;
import model.Ticket;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

import java.util.Queue;

public class BookingController {
    public static void bookSeat(){

        String name = UserInputOutputService.getUserName();
        String seatType = SeatAvailabilityController.getSeatPreference();
        Seat selected_seat =  SeatAvailabilityController.displaySeatsAndChoose(seatType);

       if( selected_seat == null ){
           addUserIntoWaitingList(name, seatType);
       }else{
           selected_seat.setStatus("BOOKED");
           Passenger passenger = new Passenger(name, seatType);
           Ticket ticket = new Ticket( passenger, selected_seat);
           RailwayReservationRepository.addTicketIntoTicketMap(ticket);

           UserInputOutputService.printTicket(ticket);
       }
    }

    private static void addUserIntoWaitingList( String name, String seatType ){
        Queue<Passenger> waitingList = RailwayReservationRepository.getWaitingList(seatType);
        int waitingListLimit = RailwayReservationRepository.getWaitingListLimit();

        if( waitingList.size() < waitingListLimit ){
            Passenger passenger = new Passenger( name, seatType );
            waitingList.add(passenger);

            UserInputOutputService
                    .printMessageAndSingleLine("✅---Successfully added into waiting list");
        }else{
            UserInputOutputService
                    .printMessageAndSingleLine("⚠️---Unable to book seat, sorry for the in convenience");
        }
    }
}
