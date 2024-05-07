package OnlineMovieBookingApp;

public abstract class User {
    private static int idCounter = 0;
    private int id;
    private String name;

    public User(String name) {
        idCounter += 1;
        this.id = idCounter;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

//-------------------------------< BookMyshow >----------------------------------

package OnlineMovieBookingApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BookMyShow {
    ArrayList<Theater> theaters;
    static HashMap<String, ArrayList<Show>> movieMap;

    private void generateMovieMap() {
        for (Theater theater : theaters) {
            ArrayList<Show> showArray = theater.getShows();
            for (Show show : showArray) {
                if (show != null) {
                    if (movieMap.containsKey(show.getMovie().getName())) {
                        movieMap.get(show.getMovie().getName()).add(show);
                    } else {
                        ArrayList<Show> movieShowList = new ArrayList<>();
                        movieShowList.add(show);
                        movieMap.put(show.getMovie().getName(),
                                movieShowList);
                    }
                }
            }
        }
    }

    public BookMyShow(ArrayList<Theater> theaters) {
        this.theaters = theaters;
        this.movieMap = new HashMap<>();
        generateMovieMap();
        // System.out.println(movieMap);
    }

    public static ArrayList<Show> searchShows(String movieName) {
        if (movieMap.containsKey(movieName)) {
            return movieMap.get(movieName);
        } else
            return null;
    }


//--------------------------------<main method>-------------------------------------------------------------
public static void main(String[] args) {
// Creating Guest User --> Piyush
    GuestUser piyush = new GuestUser("Piyush");
    RegisteredUser ayush = new RegisteredUser("Ayush");
    RegisteredUser saurabh = new RegisteredUser("Saurabh");

// Creating Movie object --> Iron Man
    Movie ironMan = new Movie("Iron Man", Language.ENGLISH, Genre.ACTION);
    Movie avengers = new Movie("Avengers: End Game", Language.ENGLISH, Genre.ACTION);
    Movie walkToRemember = new Movie("The Walk To Remember", Language.ENGLISH, Genre.ROMANCE);
    Movie housefull = new Movie("HouseFull 2", Language.HINDI, Genre.COMEDY);

// Creating Theater
    Theater pvr_gip = new Theater("PVR", "GIP Noida", 30);
    Theater big_cinema = new Theater("Big Cinema", "Sector 137 Noida", 40);

    Show show1 = null, show2 = null, show3 = null, show4 = null;
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

    try { // creating shows with respective date and time :
        String dateInString = "Friday, Jun 7, 2024 09:00:00 AM";
        Date date = formatter.parse(dateInString);
        show1 = new Show(date, ironMan, pvr_gip);

        dateInString = "Friday, Jun 7, 2024 12:00:00 PM";
        date = formatter.parse(dateInString);
        show2 = new Show(date, housefull, pvr_gip);

        // BIG-CINEMA
        dateInString = "Friday, Jun 7, 2024 09:00:00 AM";
        date = formatter.parse(dateInString);
        show3 = new Show(date, walkToRemember, big_cinema);

        // BIG-CINEMA
        dateInString = "Friday, Jun 7, 2024 12:00:00 PM";
        date = formatter.parse(dateInString);
        show4 = new Show(date, walkToRemember, big_cinema);

    } catch (ParseException e) {
        e.printStackTrace();
    }

    ArrayList<Theater> theaterArrayList = new ArrayList<>();
    theaterArrayList.add(pvr_gip);
    theaterArrayList.add(big_cinema);

    BookMyShow bookMyShow = new BookMyShow(theaterArrayList);
    ArrayList<Show> searchedShow = BookMyShow.searchShows("Iron Man");
    Show bookingShow = searchedShow.get(0);


    TicketBookingThread t1 = new TicketBookingThread(bookingShow, ayush, 10);
    TicketBookingThread t2 = new TicketBookingThread(bookingShow, saurabh, 10);
    TicketBookingThread t3 = new TicketBookingThread(bookingShow, saurabh, 10);

    t1.start();
    t2.start();
    t3.start();

    try {
        t1.join();
        t2.join();
        t3.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    Ticket ayush_ticket = t1.getTicket();
    Ticket saurabh_ticket = t2.getTicket();
    Ticket saurabh_ticket2 = t3.getTicket();

    searchedShow = BookMyShow.searchShows("HouseFull 2");
    bookingShow = searchedShow.get(0);
    TicketBookingThread t4 = new TicketBookingThread(bookingShow, ayush, 15);
    TicketBookingThread t5 = new TicketBookingThread(bookingShow, saurabh, 10);

    t4.start();

    try {
        t4.join();
        t5.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    Ticket ayushNewTicket = t4.getTicket();
    }
}


//-----------------------------------< genre>------------------------------------------------------
package OnlineMovieBookingApp;

public enum Genre {
    ACTION, ROMANCE, COMEDY, THRILLER, HORROR
}
//-----------------------------------------< guest user >--------------------------------------------
package OnlineMovieBookingApp;

public class GuestUser extends User {
    public GuestUser(String name) {
        super(name);
    }

    public void register(String username, String password) {
    }
}
//------------------------------< enum > -------------------------------------------------------
package OnlineMovieBookingApp;

public enum Language {
    TELUGU, TAMIL, ENGLISH, HINDI
}package OnlineMovieBookingApp;


//---------------------------------------< movie >-------------------------------------------------
public class Movie {
    private String name;
    private float rating = 0.0f;
    private Language language;
    private Genre genre;

    public Movie(String name, Language language, Genre genre) {
        this.name = name;
        this.language = language;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public Language getLanguage() {
        return language;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie [name=" + name + ", language=" +
                language + ", genre=" + genre + "]";
    }
}

//-----------------------------------------------< Registered user >--------------------------------------------
package OnlineMovieBookingApp;
import java.util.ArrayList;

public class RegisteredUser extends User {
    public ArrayList<Ticket> bookingHistory;

    public RegisteredUser(String name) {
        super(name);
        this.bookingHistory = new ArrayList<>();
    }

    public void cancelTicket(Ticket ticket) {
    }
}

//--------------------------------------< show >--------------------------------------------------------------------


package OnlineMovieBookingApp;
import java.util.Date;

public class Show {
    private static int idCounter = 0;
    private int id;
    private Date showTime;
    private Movie movie;
    private Theater theater;
    private int availableSeats;

    public Show(Date showTime, Movie movie, Theater theater) {
        idCounter += 1;
        this.id = idCounter;
        this.showTime = showTime;
        this.movie = movie;
        this.theater = theater;
        this.availableSeats = theater.getCapacity();
        theater.getShows().add(this);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void updateShow() {
    }

    public synchronized Ticket bookTicket(RegisteredUser user, int seats) {
        if (availableSeats >= seats && seats > 0) {
            Ticket ticket = new Ticket();
            availableSeats -= seats;
            ticket.setOwner(user.getName());
            ticket.setBookedShow(this);
            ticket.setBookingTime(new Date());
            ticket.setNumberOfSeats(seats);
            System.out.println("Successfully booked");
            user.bookingHistory.add(ticket);
            return ticket;
        } else {
            System.out.println("Seats not Available");
            return null;
        }
    }

@Override
public String toString() {
return "Show{" + "id=" + id + ", showTime=" +
showTime + ", movie=" + movie.getName() + ", theater="
+ theater.getName() + ",
availableSeats=" + availableSeats + '}';
}
}

//-------------------------------------------------< Theater >-----------------------------------

package OnlineMovieBookingApp;
import java.util.ArrayList;

public class Theater {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String location;
    private int capacity;
    private ArrayList<Show> shows;

    public Theater(String name, String location, int capacity) {
        idCounter += 1;
        this.id = idCounter;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.shows = new ArrayList<>();
    }

    public void updateShow(Show oldShow, Show newShow) {
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

@Override
public String toString() {
    return "Theater [id=" + id + ", name=" + name + ", location=" + location + ", capacity=" + capacity + ", shows=" + shows + "]";}
}

//-------------------------------------------< Ticket >------------------------------------------
package OnlineMovieBookingApp;
import java.util.Date;

public class Ticket {
    private static int idCounter = 0;
    private int id;
    private String owner;
    private Date bookingTime;
    private int numberOfSeats;
    private Show bookedShow;

    public Ticket() {
        idCounter += 1;
        this.id = idCounter;
    }

    public String getTicketInfo() {
        return null;
    }

    public int cancelTicket() {
        return 0;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Show getBookedShow() {
        return bookedShow;
    }

    public void setBookedShow(Show bookedShow) {
        this.bookedShow = bookedShow;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", owner=" + owner +
                ", bookingTime=" + bookingTime + ", numberOfSeats="
                + numberOfSeats + ", bookedShow=" +
                bookedShow + "]";
    }
}

//------------------------------------< Ticket booking thread >----------------------------------------
package OnlineMovieBookingApp;

public class TicketBookingThread extends Thread {
    private Show show;
    private RegisteredUser user;
    private int numberOfSeats;
    private Ticket ticket;

    public TicketBookingThread(Show show, RegisteredUser user, int numberOfSeats) {
        this.show = show;
        this.user = user;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public void run() {
        ticket = show.bookTicket(user, numberOfSeats);
    }

    public Ticket getTicket() {
        return ticket;
    }
}