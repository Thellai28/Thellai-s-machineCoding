package model;

import java.util.HashSet;
import java.util.Set;

public class Lift {
    private static int idGenerator = 1;

    private String liftNumber;
    private int availableAt;
    private int prevStop;
    private Set<Integer> stopsSet;
    private String liftDirection;
    private int liftTravelStartPoint;
    private int liftTravelEndPoint;

    public Lift() {
        this.liftNumber = "Lift - " + idGenerator++;
        this.availableAt = 0;
        this.prevStop = 0;
        this.stopsSet  = new HashSet<>();
        this.liftDirection = "UP";
        this.liftTravelStartPoint = -1;
        this.liftTravelEndPoint = -1;
    }

    @Override
    public String toString() {
        return String.format("%-12s| %-11d| %-8d| %-9s", liftNumber, prevStop, availableAt, liftDirection);
    }

    // --------------< Getters & Setters >-------------------------------


    public void setAvailableAt( int availableAt ) {
        this.availableAt = availableAt;
    }

    public void setPrevStop( int prevStop ) {
        this.prevStop = prevStop;
    }

    public int getAvailableAt() {
        return availableAt;
    }

    public String getLiftNumber() {
        return liftNumber;
    }

    public int getPrevStop() {
        return prevStop;
    }

    public Set<Integer> getStopsSet() {
        return stopsSet;
    }

    public String getLiftDirection() {
        return liftDirection;
    }

    public void setLiftDirection( String liftDirection ) {
        this.liftDirection = liftDirection;
    }

    public int getLiftTravelEndPoint() {
        return liftTravelEndPoint;
    }

    public void setLiftTravelEndPoint( int liftTravelEndPoint ) {
        this.liftTravelEndPoint = liftTravelEndPoint;
    }

    public int getLiftTravelStartPoint() {
        return liftTravelStartPoint;
    }

    public void setLiftTravelStartPoint( int liftTravelStartPoint ) {
        this.liftTravelStartPoint = liftTravelStartPoint;
    }
}
