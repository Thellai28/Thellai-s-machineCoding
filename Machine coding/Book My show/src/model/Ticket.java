package model;

public class Ticket {
    private static int staticId = 1;
    private int id;
    private String UserName;
    private Show show;
    private int seatsBooked;

    public Ticket( int seatsBooked, Show show, String userName ) {
        this.id = staticId;
        staticId++;
        this.seatsBooked = seatsBooked;
        this.show = show;
        UserName = userName;
    }

    //----------------<Getters & setters>---------------------------------

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked( int seatsBooked ) {
        this.seatsBooked = seatsBooked;
    }

    public Show getShow() {
        return show;
    }

    public void setShow( Show show ) {
        this.show = show;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName( String userName ) {
        UserName = userName;
    }
}
