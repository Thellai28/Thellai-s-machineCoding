package controller;

import model.Movie;
import model.Show;
import model.Theater;
import model.Ticket;
import repository.BookMyShowRepository;
import service.AddMovieService;
import service.BookingService;
import service.UserIoService;

import java.util.List;

public class BookMyShowController {

    public static void handleBookingRequest(){

        Theater chosenTheater = BookingService.selectTheTheater();
        String chosenMovie = BookingService.selectTheMovie(chosenTheater);
        String preferredLanguage = BookingService.selectTheLanguage(chosenTheater);
        int requiredSeats = BookingService.getRequiredQuantityOfSeats();

        Ticket bookedTicket = BookingService.bookMovie(chosenMovie, preferredLanguage, requiredSeats,
                chosenTheater.getShowList());

        if( bookedTicket != null ){
            BookingService.saveTicket(bookedTicket);
            BookingService.printTicket(bookedTicket);
        }
    }

    public static void handleAddMovieRequest(){
        String movieName = UserIoService.getMovieNameFromUser();
        List<String> supportedLanguagesList = UserIoService.getSupportedLanguagesListFromUser();
        UserIoService.printHorizontalLines();

        List<Movie> movieList = AddMovieService.createMovieList(movieName, supportedLanguagesList);
        List<Theater> theaterList = BookMyShowRepository.getAllAvailableTheaters();

        AddMovieService.createShowsForNewMovieInTheater(theaterList, movieList);
    }

    public static void handleCancelMovieRequest(){
        int ticketId = UserIoService.getTicketIdFromUser();
        Ticket ticket = BookMyShowRepository.getTicketFromTicketMap(ticketId);

        if( ticket != null ){
            int numberOfBookedSeats = ticket.getSeatsBooked();
            Show bookedShow = ticket.getShow();
            bookedShow.setSeatsAvailable(bookedShow.getSeatsAvailable() + numberOfBookedSeats);
            System.out.println("🎉🎉🎉 Successfully cancelled the movie");
        }else{
            System.out.println("☹️☹️ Ticket id is invalid");
        }
    }

}
