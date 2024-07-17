package model;

public class Seat {
    private static int globalSeatNumberGenerator = 1;

    private String seatNumber;
    private String status;
    private String type;

    public Seat( String type ) {
        this.seatNumber = type + "-" + globalSeatNumberGenerator++;
        this.status = "AVAILABLE";
        this.type = type;
    }

    //-------------< Getters & Setters >----------------------------

    public String getSeatNumber() {
        return seatNumber;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

}
