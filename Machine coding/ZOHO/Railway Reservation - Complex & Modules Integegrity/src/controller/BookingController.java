package controller;

import model.Passenger;
import model.RailwayReservationSystem;
import model.Seat;
import model.Ticket;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    public static void handleBooking(){
        char from = UserInputOutputService.getStation("😀-- Please enter your source station");
        char to = UserInputOutputService.getStation("😀-- Please enter your destination station");
        int ticketsRequired = UserInputOutputService.getTotalSeatRequired();

        processBooking(ticketsRequired, from, to);

    }

    public static boolean processBooking( int ticketsRequired, char from, char to){
        List<Seat> availableSeats = fetchSeats( from, to );

        if( availableSeats.size() >= ticketsRequired ){
            bookTickets(availableSeats, from, to, ticketsRequired);
            return true;

        }else {

            int waitingListFreeSlots = RailwayReservationRepository
                    .railwayReservationSystem.getWaitingList().size();

            int availableFreeSlots = RailwayReservationSystem.getWaitingListLimit() - waitingListFreeSlots;

            if( availableFreeSlots >= ticketsRequired ){
                for( int i = 0; i < ticketsRequired; i++ ){
                    Passenger passenger = new Passenger(from, to, ticketsRequired);
                    RailwayReservationRepository.railwayReservationSystem.getWaitingList().add(passenger);
                }

                UserInputOutputService
                        .printMessageAndOneLine("✅-- successfully added user into waiting list");
                return true;
            }else{
                UserInputOutputService
                        .printMessageAndOneLine("❌-- Sorry, no seats available for booking");
                return false;
            }
        }

    }

    private static int calculateStationsBetween( char from, char to ){
        int numFrom = getDestinationIndex(from);
        int numTo = getDestinationIndex(to);
        int stops = numTo - numFrom + 1; // From station will not be calculated, so adding it into consideration :

        return stops;

    }

    private static List<Seat> fetchSeats( char from, char to ){
       List<Seat> seatList =  RailwayReservationRepository
                .railwayReservationSystem.getSeatList();

       int totalStops = calculateStationsBetween(from, to);

        List<Seat> availableSeats = new ArrayList<>();
       for( Seat seat : seatList ){
           int stops = calculateStopsAvailable(seat, from, to );

           if( stops >= totalStops -1 ){
               availableSeats.add(seat);
           }
       }
       return availableSeats;
    }

    private static int calculateStopsAvailable(Seat seat, char from, char to ){
        int stops = 0;
        int start = getDestinationIndex(from);
        int end = getDestinationIndex(to);

        for( int i = start; i <= end; i++ ){
            if(seat.getAvailableStops()[i] == true ){
                stops++;
            }
        }
        return stops;
    }

    private static void bookTickets( List<Seat> seats,
                                     char from, char to, int ticketRequired ){

        Ticket ticket = new Ticket( from, to);
        for( int i = 0; i < ticketRequired; i++ ){
            Seat seat = seats.get(i);
            blockBookedStations(seat, from, to);
            ticket.getSeatsBooked().add(seat.getSeatId());
        }

        UserInputOutputService.printTicket(ticket);
        RailwayReservationRepository
                .railwayReservationSystem.getTicketMap().put(ticket.getPnr(),ticket);
        UserInputOutputService.printMessageAndOneLine("✅-- Ticket  added successfully");
    }

    private static void blockBookedStations( Seat seat, char from, char to ){
        int start = getDestinationIndex(from);
        int end = getDestinationIndex(to);

        for( int j = start; j <= end; j++ ){
            seat.getAvailableStops()[j] = false;
        }

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
}
