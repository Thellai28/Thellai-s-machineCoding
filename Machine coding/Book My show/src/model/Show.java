package model;

public class Show {
    private Movie movie;
    private int seatsAvailable;

    public Show( Movie movie, int seatsAvailable ) {
        this.movie = movie;
        this.seatsAvailable = seatsAvailable;
    }


    // ------------------<Getters & Setters>----------------------------------

    public Movie getMovie() {
        return movie;
    }

    public void setMovie( Movie movie ) {
        this.movie = movie;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable( int seatsAvailable ) {
        this.seatsAvailable = seatsAvailable;
    }
}
