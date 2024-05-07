package repository;

import model.BookMyShow;
import model.Theater;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class BookMyShowRepository {
    private static BookMyShow bookMyShow;

    public static void initializeBookMyShowService(){
        bookMyShow = new BookMyShow();
    }

    public static List<Theater> getAllAvailableTheaters(){
        List<Theater> theaterList = new ArrayList<>();

        for( Theater theater :  bookMyShow.getTheaterMap().values() ){
            theaterList.add( theater);
        }
        return theaterList;
    }

    public static void addTicketIntoTicketMap( Ticket ticket ){
        bookMyShow.getTicketMap().put(ticket.getId(), ticket);
    }

    public static Ticket getTicketFromTicketMap(int ticketId){
        return bookMyShow.getTicketMap().get(ticketId);
    }

}
