package service;

import model.Movie;
import model.Show;
import model.Theater;
import model.Ticket;
import repository.BookMyShowRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingService {

    public static Theater selectTheTheater(){
        List<Theater> theaterList = BookMyShowRepository.getAllAvailableTheaters();
        int theaterIndex = UserIoService.displayTheaterNamesAndGetResponse(theaterList);
        UserIoService.printHorizontalLines();
        return theaterList.get(theaterIndex);
    }

    public static String selectTheMovie( Theater chosenTheater ){
        List<String>movieList = getMoviesNamesListFromShows(chosenTheater.getShowList());
        int chosenMovieIdx = UserIoService.displayListAndGetResponse(movieList);
        UserIoService.printHorizontalLines();

        return movieList.get(chosenMovieIdx);
    }

    public static String selectTheLanguage( Theater chosenTheater ){
        List<String>languageList = getLanguageListFromShows(chosenTheater.getShowList());
        int preferredLanguageIdx = UserIoService.displayListAndGetResponse(languageList);
        UserIoService.printHorizontalLines();

        return languageList.get(preferredLanguageIdx);
    }

    public static void saveTicket( Ticket bookedTicket ){
        BookMyShowRepository.addTicketIntoTicketMap(bookedTicket);
    }

    public static void printTicket( Ticket bookedTicket ){
        UserIoService.printTicketDetails(bookedTicket);
        UserIoService.printHorizontalLines();
    }

    public static int getRequiredQuantityOfSeats(){
        int requiredSeats = UserIoService.getRequiredSeatQuantityFromUser();
        UserIoService.printHorizontalLines();
        return requiredSeats;
    }

    private static List<String> getLanguageListFromShows( List<Show> showList ){
        Set<String> languageSet = new HashSet<>();
        for( Show show : showList){
            languageSet.add( show.getMovie().getLanguage());
        }
        return new ArrayList<>(languageSet);
    }

    private static List<String> getMoviesNamesListFromShows( List<Show> showList ){
        Set<String> movieNameSet = new HashSet<>();
        for( Show show : showList ){
            movieNameSet.add(show.getMovie().getName());
        }
        return new ArrayList<>(movieNameSet);
    }

    public static Ticket bookMovie( String movieName, String preferredLanguage,
                                    int seatQuantity, List<Show> showList ){
        for( Show currShow : showList ){
            Movie movie =  currShow.getMovie();
            if( movie.getName().equalsIgnoreCase(movieName)
                    && movie.getLanguage().equalsIgnoreCase(preferredLanguage)
                    && currShow.getSeatsAvailable() >= seatQuantity){
                currShow.setSeatsAvailable(currShow.getSeatsAvailable() - seatQuantity);
                return createTicketForBookedShow(currShow, seatQuantity);
            }
        }
        System.out.println("☹️☹️ Sorry, movie with you preference is not available, " +
                "try some other movie");
        UserIoService.printHorizontalLines();
        return null;
    }

    private static Ticket createTicketForBookedShow( Show chosenShow,  int seatQuantity){
        String userName = UserIoService.getUserName();
        Ticket ticket = new Ticket(seatQuantity, chosenShow, userName );
        System.out.println("🎉🎉🎉 Your show booking is confirmed & your ticket id is "+ ticket.getId());
        UserIoService.printHorizontalLines();
        return ticket;
    }
}
