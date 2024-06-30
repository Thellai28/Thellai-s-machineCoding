package repository;

import model.Passenger;
import model.RailwayReservation;
import model.Seat;
import model.Ticket;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RailwayReservationRepository {
    private static RailwayReservation railwayReservation;

    public static void initiateRailwayReservation(){
        railwayReservation = new RailwayReservation();
        UserInputOutputService
                .printMessageAndSingleLine("✅---Railway reservation initialized successfully---");
    }

    public static List<Seat> getSeatList( String type ){
        if( type.equals("AC")){
            return railwayReservation.getAcSeatList();
        }else if( type.equals("NON-AC")){
            return railwayReservation.getNonAcSeatList();
        }
        return railwayReservation.getSeaterList();
    }

    public static Queue<Passenger> getWaitingList( String type ){
        if( type.equals("AC")){
            return railwayReservation.getAcWaitingList();
        }else if( type.equals("NON-AC")){
            return railwayReservation.getNonAcWaitingList();
        }
        return railwayReservation.getSeaterWaitingList();
    }

    public static int getWaitingListLimit(){
        return railwayReservation.getWAITING_LIST_LIMIT();
    }

    public static void addTicketIntoTicketMap( Ticket ticket ){
        railwayReservation
                .getTicketMap()
                .put( ticket.getTicketId(), ticket );
    }

    public static Ticket getTicketTicketMap( int ticketId ){
        return railwayReservation.getTicketMap().get(ticketId);
    }

    public static List<Ticket> getAllTicketsFromMap(){
        return new ArrayList<>(
                railwayReservation.getTicketMap().values()
        );
    }
}
