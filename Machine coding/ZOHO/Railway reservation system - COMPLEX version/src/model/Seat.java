package model;

import java.util.ArrayList;
import java.util.List;

public class Seat {
    private int seatNumber;
    private String berth;
    private int seatCapacity;
    private List<Passenger> passengerList;

    public Seat( int seatCapacity, int seatNumber, String berth ) {
        this.seatCapacity = seatCapacity;
        this.seatNumber = seatNumber;
        this.berth = berth;
        this.passengerList = new ArrayList<>();
    }


    //------------< Getters & setters >----------------------------------------


    public List<Passenger> getUserList() {
        return passengerList;
    }


    public int getSeatCapacity() {
        return seatCapacity;
    }


    public int getSeatNumber() {
        return seatNumber;
    }


    public String getBerth() {
        return berth;
    }

}
