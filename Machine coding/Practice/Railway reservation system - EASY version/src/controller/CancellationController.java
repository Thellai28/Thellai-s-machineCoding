package controller;

import model.Passenger;
import model.RailwayReservation;
import model.Seat;
import model.Ticket;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

import java.util.Queue;

public class CancellationController {
    public static void cancelTicket(){
        int ticketId = UserInputOutputService.getTicketId();
        Ticket ticketToCancel = RailwayReservationRepository
                .getTicketTicketMap(ticketId);

        if( ticketToCancel != null ){
            ticketToCancel.setStatus("CANCELLED");
            Seat cancelledSeat = ticketToCancel.getSeat();
            cancelledSeat.setStatus("AVAILABLE");

            UserInputOutputService.printMessageAndSingleLine("✅---Ticket cancelled---");


            movePassengerFromWaitingList(cancelledSeat);
        }else {
            UserInputOutputService.printMessageAndSingleLine("⚠️---Invalid ticket number---⚠️");
        }
    }

    private static void movePassengerFromWaitingList( Seat cancelledSeat ){
        String seatType = cancelledSeat.getType();
        Queue<Passenger> waitingList = RailwayReservationRepository.getWaitingList(seatType);


        if( !waitingList.isEmpty() ){
            Passenger confirmedPassenger = waitingList.remove();
            cancelledSeat.setStatus("BOOKED");

            Ticket ticket = new Ticket( confirmedPassenger, cancelledSeat );
            RailwayReservationRepository.addTicketIntoTicketMap(ticket);

            UserInputOutputService.printMessageAndSingleLine("Moving " + confirmedPassenger.getName() +
                    " from waiting list to Confirmed seat");
            UserInputOutputService.printTicket(ticket);
        }
    }
}
