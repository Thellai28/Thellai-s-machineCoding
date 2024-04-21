package railwayReservation.Service;

import railwayReservation.model.Passenger;

import java.util.*;

import static railwayReservation.Service.DisplayService.seatAvailability;

public class TicketBookingService {
    public  static int berthLimit = 6/3; // the total berth value will be shared between upper, lower and middle.
    private static int racLimit = 1;
    private static int waitingListLimit = 1;

    private static int upperSeatNumber = 1;
    private static int middleSeatNumber = 2;
    private static int  lowerSeatNumber = 3;

    public static List<Passenger>confirmationList = new ArrayList<>();
    public static List<Passenger>upperList = new ArrayList<>();
    public static List<Passenger>middleList = new ArrayList<>();
    public static List<Passenger>lowerList = new ArrayList<>();

    public static Queue<Passenger> racQueue = new LinkedList<>();
    public static Queue<Passenger> waitingQueue = new LinkedList<>();

    public static void bookTicket(Passenger passenger){
        if( upperList.size() == berthLimit && lowerList.size() == berthLimit && middleList.size() == berthLimit ){
            if( updateRacQueue(passenger) ){
                System.out.println("Added to RAC \n Your ticket id is " + passenger.getTicketNumber());
            }else if( updateWaitingListQueue(passenger)){
                System.out.println("Added to waiting list \n Your ticket id is " + passenger.getTicketNumber() );
            }else{
                passenger.setTicketNumber(passenger.getTicketNumber() -1);
                System.out.println("Tickets not available");
            }
        }else if( checkAvailabilityAndAssignSeat(passenger)){
            System.out.println("Booking confirmed \n your ticket id is " + passenger.getTicketNumber());
            passenger.setTicketType("berth");
            confirmationList.add(passenger);
        }else{
            System.out.println(passenger.getSeatPreference() + " is not available " );
            passenger.setTicketNumber(passenger.getTicketNumber()-1);
            seatAvailability(); // Instead of having this is then main method, having it here is a clever move.
        }
    }

    private static boolean updateRacQueue( Passenger p ){
        if( racQueue.size() < racLimit ){
            racQueue.add( p);
            p.setTicketType("rac");
            return true;
        }
        return false;
    }

    private static boolean updateWaitingListQueue( Passenger p ){
        if(waitingQueue.size() < waitingListLimit ){
            waitingQueue.add( p );
            p.setTicketType("Waiting list");
            return true;
        }
        return false;
    }

    private static boolean checkAvailabilityAndAssignSeat( Passenger p ){
        Map<Integer, Character> map = TicketCancelingService.getCancelledTicketsMap();

        if(p.getSeatPreference() == 'U'){
            if( upperList.size() < berthLimit ){
                if(!map.isEmpty() ){
                    getAndSetSeatDetails( map, p );
                }else{
                    p.setSeatNumber(upperSeatNumber);
                    upperSeatNumber += 3;
                }
                upperList.add(p);
                return true;
            }
        }else if ( p.getSeatPreference() == 'M' ){
            if( middleList.size() < berthLimit ){
                if( !map.isEmpty() ){
                    getAndSetSeatDetails(map, p);
                }else{
                    p.setSeatNumber(middleSeatNumber);
                    middleSeatNumber += 3;
                }
                middleList.add(p);
                return true;
            }
        }else{
            if( lowerList.size() < berthLimit ){
                if( !map.isEmpty() ){
                    getAndSetSeatDetails(map, p);
                }else{
                    p.setSeatNumber(lowerSeatNumber);
                    lowerSeatNumber += 3;
                }
                lowerList.add(p);
                return true;
            }
        }
        return false;
    }

    private static void getAndSetSeatDetails(Map<Integer, Character> map, Passenger p ){
        int seatNumber = getPreferenceSeatNumber(map, p.getSeatPreference());
        p.setSeatNumber(seatNumber);
        map.remove(seatNumber);
    }
    private static int getPreferenceSeatNumber(Map<Integer,Character> map, char preference){
        int seatNumber = 0;

        for( int key : map.keySet() ){
            if( preference == (char)map.get(key) ){
                seatNumber = (int)key;
                break;
            }
        }
        return seatNumber;
    }

}
