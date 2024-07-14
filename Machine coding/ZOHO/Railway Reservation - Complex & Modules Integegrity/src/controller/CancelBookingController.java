package controller;

import model.Passenger;
import model.Seat;
import model.Ticket;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;


public class CancelBookingController {
    public static void handleCancel(){
        int pnrNumber = UserInputOutputService.getTicketId();
        Ticket ticket = RailwayReservationRepository
                .railwayReservationSystem.getTicketMap().get(pnrNumber);

        if( ticket != null ){
            int cancelNumbers = UserInputOutputService.getCancelCount();
            cancelSeats(ticket, cancelNumbers);

            moveWaitingToConfirmation();

        }else{
            UserInputOutputService
                    .printMessageAndOneLine("❌-- Invalid ticket id : ");
        }
    }

    private static void cancelSeats( Ticket ticket, int cancelNumber ){
        List<Integer> bookedSeats = ticket.getSeatsBooked();
        List<Seat> seatList = RailwayReservationRepository.railwayReservationSystem.getSeatList();

        for( int i = 0; i < cancelNumber; i++ ){
            int seatNumber = bookedSeats.get(i);
            Seat seat = fetchSeat(seatNumber, seatList );
            makeSeatsAvailable(seat, ticket.getSource(), ticket.getDestination());
        }
    }

    private static Seat fetchSeat( int seatNumber , List<Seat> seatList ){
        for( Seat seat : seatList ){
            if( seat.getSeatId() == seatNumber ){
                return seat;
            }
        }
        return null;
    }

    private static void makeSeatsAvailable( Seat seat, char source, char destination ){
        int from = getDestinationIndex(source);
        int to = getDestinationIndex(destination);

        for( int i = from; i <= to; i++ ){
            seat.getAvailableStops()[i] = true;
        }
        UserInputOutputService.printMessageAndOneLine("✅-- seat freed up successfully");
    }

    private static int getDestinationIndex( char c ){
        if( c == 'A'){
            return 1;
        }else if( c == 'B'){
            return 2;
        }else if( c == 'C'){
            return 3;
        }else if( c == 'D'){
            return 4;
        }else{
            return 5;
        }
    }

    private static void moveWaitingToConfirmation(){
        List<Passenger> waitingList = RailwayReservationRepository.railwayReservationSystem.getWaitingList();
        List<Passenger> movedPassengers = new ArrayList<>();

        for( Passenger passenger : waitingList ){
           boolean bookingStatus =  BookingController.processBooking(passenger.getTicketsRequired()
                    , passenger.getSource(), passenger.getDestination());

           if(bookingStatus){
               movedPassengers.add(passenger);
               UserInputOutputService.printMessageAndOneLine("✅-- Users Moved form waiting list to confirmation");
           }
        }

        for( Passenger movedPass : movedPassengers ){
            int idx = getIndexOfPassengerShouldRemove(movedPass, waitingList);
            waitingList.remove(idx);
        }
    }

    private static int getIndexOfPassengerShouldRemove( Passenger removable, List<Passenger>waitingList ){
        for( int i = 0; i < waitingList.size(); i++ ){
            Passenger pass = waitingList.get(i);
            if( removable.getId() == pass.getId() ){
                return i;
            }
        }
        return 0;
    }
}
