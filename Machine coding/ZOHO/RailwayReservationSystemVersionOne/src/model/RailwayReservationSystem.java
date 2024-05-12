package model;

import java.util.*;

public class RailwayReservationSystem {
    private List<Seat> seatList;
    private Map<Integer, Ticket> ticketMap;
    private Queue<User> racQueue;
    private Queue<User> waitingListQueue;
    private List<User> childrenList;
    private final int RAC_LIMIT = 18;
    private final int WAITING_LIST_LIMIT = 10;

    public RailwayReservationSystem() {

        this.ticketMap = new HashMap<>();
        this.racQueue = new LinkedList<>();
        this.waitingListQueue = new LinkedList<>();
        this.childrenList = new ArrayList<>();
        this.seatList = generateSeats();
    }

    private List<Seat> generateSeats(){
        List<Seat> seatList = new ArrayList<>();
        final String[] seatCategory = {"LOWER", "MIDDLE", "UPPER", "UPPER", "UPPER", "MIDDLE", "LOWER", "RAC" };
        final int TOTAL_SEATS = 72; // 63 + 9 :

        for( int i = 0; i < TOTAL_SEATS; i++  ){
            int seatNumber = i+1;
            int categoryIndex = i % seatCategory.length;
            int seatCapacity = ( categoryIndex == 7 ) ? 2 : 1;
            String seatType = seatCategory[categoryIndex];
            Seat newSeat = new Seat(seatCapacity, seatNumber, seatType );

            seatList.add( newSeat );
        }
        return seatList;
    }


    //-----------------------------< Getters & setters >-------------------------------------------------

    public int getRAC_LIMIT() {
        return RAC_LIMIT;
    }

    public Queue<User> getRacQueue() {
        return racQueue;
    }

    public void setRacQueue( Queue<User> racQueue ) {
        this.racQueue = racQueue;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList( List<Seat> seatList ) {
        this.seatList = seatList;
    }

    public Map<Integer, Ticket> getTicketMap() {
        return ticketMap;
    }

    public void setTicketMap( Map<Integer, Ticket> ticketMap ) {
        this.ticketMap = ticketMap;
    }

    public int getWAITING_LIST_LIMIT() {
        return WAITING_LIST_LIMIT;
    }

    public Queue<User> getWaitingListQueue() {
        return waitingListQueue;
    }

    public void setWaitingListQueue( Queue<User> waitingListQueue ) {
        this.waitingListQueue = waitingListQueue;
    }

    public List<User> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList( List<User> childrenList ) {
        this.childrenList = childrenList;
    }
}
