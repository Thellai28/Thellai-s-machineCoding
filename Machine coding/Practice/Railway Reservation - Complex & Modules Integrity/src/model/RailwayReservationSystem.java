package model;

import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RailwayReservationSystem {
    List<Seat>seatList;
    private Map<Integer, Ticket> ticketMap;
    List<Passenger> waitingList;
    static int waitingListLimit = 2;
    static int totalSeats = 8;
    static int availableStops = 6;


    public RailwayReservationSystem() {
        this.seatList = new ArrayList<>();
        this.ticketMap = new HashMap<>();
        this.waitingList = new ArrayList<>();

        fillSeatList();
        UserInputOutputService
                .printMessageAndOneLine("✅-- Railway reservation system object created successfully");

    }

    private void fillSeatList(){
        for( int i = 0; i < totalSeats; i++ ){
            Seat  seat = new Seat(availableStops);
            this.seatList.add(seat);
        }
    }


    public static int getAvailableStops() {
        return availableStops;
    }

    public static void setAvailableStops( int availableStops ) {
        RailwayReservationSystem.availableStops = availableStops;
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

    public static int getTotalSeats() {
        return totalSeats;
    }

    public static void setTotalSeats( int totalSeats ) {
        RailwayReservationSystem.totalSeats = totalSeats;
    }

    public List<Passenger> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList( List<Passenger> waitingList ) {
        this.waitingList = waitingList;
    }

    public static int getWaitingListLimit() {
        return waitingListLimit;
    }

    public static void setWaitingListLimit( int waitingListLimit ) {
        RailwayReservationSystem.waitingListLimit = waitingListLimit;
    }
}
