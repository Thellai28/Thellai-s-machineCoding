package models;

public class Event {
    private String eventDescription;
    private int duration;

    public Event( int duration, String eventDescription ) {
        this.duration = duration;
        this.eventDescription = eventDescription;
    }


    //-----------< Getters & Setters >--------------------------

    public int getDuration() {
        return duration;
    }

    public void setDuration( int duration ) {
        this.duration = duration;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription( String eventDescription ) {
        this.eventDescription = eventDescription;
    }
}
