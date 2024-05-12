package model;

import java.util.ArrayList;
import java.util.List;

public class Seat {
    private int seatNumber;
    private String berth;
    private int seatCapacity;
    private List<User> userList;

    public Seat( int seatCapacity, int seatNumber, String berth ) {
        this.seatCapacity = seatCapacity;
        this.seatNumber = seatNumber;
        this.berth = berth;
        this.userList = new ArrayList<>();
    }


    //------------< Getters & setters >----------------------------------------


    public List<User> getUserList() {
        return userList;
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
