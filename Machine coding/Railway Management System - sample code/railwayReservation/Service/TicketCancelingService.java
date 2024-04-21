package railwayReservation.Service;

import railwayReservation.model.Passenger;

import java.util.HashMap;
import java.util.Map;

public class TicketCancelingService {
    static char cancelledSeatPreference = '0';
    static int cancelledSeatNumber = 0;

    private static Map<Integer, Character> cancelledTicketsMap = new HashMap<>();

    public static String cancelling( int id ){
        for( Passenger p : TicketBookingService.confirmationList ){
            if( p.getTicketNumber() == id){
                cancel(p);
                return "success";
            }
        }

        for( Passenger p : TicketBookingService.racQueue){
            if( p.getTicketNumber() == id ){
                cancel(p);
                return "Success";
            }
        }

        for( Passenger p : TicketBookingService.waitingQueue){
            if( p.getTicketNumber() == id ){
                cancel(p);
                return "Success";
            }
        }

        return "In-valid seat number ";
    }



    private static void cancel( Passenger passenger ){
        if( passenger.getTicketType().equals("berth") ){
            cancelledSeatPreference = passenger.getSeatPreference();
            cancelledSeatNumber = passenger.getSeatNumber();

            cancelledTicketsMap.put(cancelledSeatNumber, cancelledSeatPreference);

            removeFromAllList(passenger);
            addRacToBerth(TicketBookingService.racQueue.poll());
            addWaitingToRac(TicketBookingService.waitingQueue.poll());
        }else if( passenger.getTicketType() == "rac" ){
            TicketBookingService.racQueue.remove(passenger);
            addWaitingToRac(TicketBookingService.waitingQueue.poll());
        }else{
            TicketBookingService.waitingQueue.poll();
        }
    }


    private static void removeFromAllList( Passenger passenger ){
        TicketBookingService.confirmationList.remove(passenger);
        TicketBookingService.upperList.remove(passenger);
        TicketBookingService.middleList.remove( passenger );
        TicketBookingService.lowerList.remove( passenger );
    }


    private static void addRacToBerth( Passenger passenger ){
        if( passenger != null ){
            passenger.setSeatPreference(cancelledSeatPreference);
            passenger.setSeatNumber(cancelledSeatNumber);
            passenger.setTicketType("berth");

            if( cancelledSeatPreference == 'U'){
                TicketBookingService.upperList.add(passenger);
            }else if( cancelledSeatPreference == 'M'){
                TicketBookingService.middleList.add(passenger);
            }else {
                TicketBookingService.lowerList.add(passenger);
            }

            TicketBookingService.confirmationList.add(passenger);
            cancelledTicketsMap.remove(cancelledSeatNumber);
        }
    }



    private static void addWaitingToRac( Passenger passenger ){
        if( passenger != null ){
            passenger.setTicketType("rac");
            TicketBookingService.racQueue.add(passenger);
        }
    }


    public static Map<Integer, Character> getCancelledTicketsMap() {
        return cancelledTicketsMap;
    }
}
