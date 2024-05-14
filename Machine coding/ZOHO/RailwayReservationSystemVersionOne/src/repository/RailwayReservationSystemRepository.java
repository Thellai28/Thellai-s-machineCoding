package repository;

import model.Passenger;
import model.RailwayReservationSystem;
import model.Seat;
import model.Ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public class RailwayReservationSystemRepository {
    private static RailwayReservationSystem railwayReservationSystem;

    public static void initializeRailwayReservationSystem(){
        railwayReservationSystem = new RailwayReservationSystem();
    }

    public static List<Seat> getAllSeats(){
        return  railwayReservationSystem.getSeatList();
    }

    public static Seat getPreferredSeat( String berthPreference ){
        for( Seat seat : railwayReservationSystem.getSeatList() ){
            if( seat.getBerth().equalsIgnoreCase(berthPreference)
                    && seat.getUserList().size() < seat.getSeatCapacity()  ){
                return seat;
            }
        }
        return null;
    }

    public static boolean isRacFull(  ){
       return railwayReservationSystem.getRacQueue().size() >= railwayReservationSystem.getRAC_LIMIT();
    }

    public static boolean isWaitingListFull( ){
        return railwayReservationSystem.getWaitingListQueue().size() >= railwayReservationSystem.getWAITING_LIST_LIMIT();
    }

    public static void addIntoWaitingListQueue( Passenger passenger ){
        railwayReservationSystem.getWaitingListQueue().add(passenger);
    }

    public static void addIntoRacQueue( Passenger passenger ){
            railwayReservationSystem.getRacQueue().add(passenger);
    }

    public static void addTicketIntoTicketMap( Ticket ticket ){
        railwayReservationSystem.getTicketMap().put( ticket.getTicketNumber(), ticket );
    }


    public static List<Passenger> getChildList(){
        return railwayReservationSystem.getChildrenList();
    }

    public static Ticket getTicket( int ticketNumber ){
        return railwayReservationSystem.getTicketMap().get(ticketNumber);
    }

    public static Passenger getUserFromRacQueue(){
        Queue<Passenger> racQueue = railwayReservationSystem.getRacQueue();
        if( !racQueue.isEmpty()) {
            return railwayReservationSystem.getRacQueue().remove();
        }
        return null;
    }

    public static Passenger getUserFromWaitingList(){
        Queue<Passenger> waitingListQueue = railwayReservationSystem.getWaitingListQueue();
        if( !waitingListQueue.isEmpty() ){
            return waitingListQueue.remove();
        }
        return null;
    }

    public static List<Ticket> getAllBookedTickets(){
        Collection<Ticket> tickets = railwayReservationSystem.getTicketMap().values();
        return new ArrayList<>(tickets);
    }

}
