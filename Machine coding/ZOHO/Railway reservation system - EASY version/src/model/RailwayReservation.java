package model;

import java.util.*;

public class RailwayReservation {
    private List<Seat> acSeatList;
    private List<Seat>nonAcSeatList;
    private List<Seat>seaterList;

    private Queue<Passenger> acWaitingList;
    private Queue<Passenger> nonAcWaitingList;
    private Queue<Passenger> seaterWaitingList;

    private Map<Integer, Ticket> ticketMap;

    private final int TOTAL_SEATS =1;
    private final int WAITING_LIST_LIMIT = 1;

    public RailwayReservation() {
        this.acSeatList = new ArrayList<>();
        this.nonAcSeatList = new ArrayList<>();
        this.seaterList = new ArrayList<>();

        this.acWaitingList = new LinkedList<>();
        this.nonAcWaitingList = new LinkedList<>();
        this.seaterWaitingList = new LinkedList<>();

        this.ticketMap = new HashMap<>();

        populateSeats();
    }

    private void populateSeats(){
        for( int i = 0; i < TOTAL_SEATS; i++ ){
            acSeatList.add( new Seat("AC"));
            nonAcSeatList.add( new Seat("NON-AC"));
            seaterList.add( new Seat("SEATER"));
        }
    }

    //----------------< Getters >-------------------------

    public List<Seat> getAcSeatList() {
        return acSeatList;
    }

    public Queue<Passenger> getAcWaitingList() {
        return acWaitingList;
    }

    public List<Seat> getNonAcSeatList() {
        return nonAcSeatList;
    }

    public Queue<Passenger> getNonAcWaitingList() {
        return nonAcWaitingList;
    }

    public List<Seat> getSeaterList() {
        return seaterList;
    }

    public Queue<Passenger> getSeaterWaitingList() {
        return seaterWaitingList;
    }

    public int getTOTAL_SEATS() {
        return TOTAL_SEATS;
    }

    public int getWAITING_LIST_LIMIT() {
        return WAITING_LIST_LIMIT;
    }

    public Map<Integer, Ticket> getTicketMap() {
        return ticketMap;
    }
}
