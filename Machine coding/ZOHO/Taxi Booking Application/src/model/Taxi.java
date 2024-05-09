package model;

public class Taxi {
    private static int taxiId = 1;
    private int taxiNumber;
    private char availableLocation = 'A';
    private int availableTime;
    private Status status;
    private int totalMoneyEarned;

    private final int DISTANCE_BETWEEN_POINTS = 15;
    private final int POINT_TO_POINT_TRAVEL_TIME = 60;
    private final int INITIAL_5KMS_CHARGE = 100;
    private final int SUBSEQUENT_KMS_CHARGE = 10;

    public Taxi() {
        this.taxiNumber = taxiId++;
        this.availableTime = 0;
        this.status = Status.AVAILABLE;
        this.totalMoneyEarned = 0;
    }


    //-----------< getters & setters>--------------------------

    public char getAvailableLocation() {
        return availableLocation;
    }

    public void setAvailableLocation( char availableLocation ) {
        this.availableLocation = availableLocation;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime( int availableTime ) {
        this.availableTime = availableTime;
    }

    public int getDISTANCE_BETWEEN_POINTS() {
        return DISTANCE_BETWEEN_POINTS;
    }

    public int getINITIAL_5KMS_CHARGE() {
        return INITIAL_5KMS_CHARGE;
    }

    public int getPOINT_TO_POINT_TRAVEL_TIME() {
        return POINT_TO_POINT_TRAVEL_TIME;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus( Status status ) {
        this.status = status;
    }

    public int getSUBSEQUENT_KMS_CHARGE() {
        return SUBSEQUENT_KMS_CHARGE;
    }

    public static int getTaxiId() {
        return taxiId;
    }

    public static void setTaxiId( int taxiId ) {
        Taxi.taxiId = taxiId;
    }

    public int getTaxiNumber() {
        return taxiNumber;
    }

    public void setTaxiNumber( int taxiNumber ) {
        this.taxiNumber = taxiNumber;
    }

    public int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned( int totalMoneyEarned ) {
        this.totalMoneyEarned = totalMoneyEarned;
    }
}
