package service;

import model.Movie;
import model.Show;
import model.Theater;

import java.util.ArrayList;
import java.util.List;

public class AddMovieService {

    public static List<Movie> createMovieList( String movieName, List<String> supportedLanguagesList){
        List<Movie> movieList = new ArrayList<>();

        for( int i = 0; i < supportedLanguagesList.size(); i++ ){
            Movie movie = new Movie(supportedLanguagesList.get(i), movieName);
            movieList.add( movie );
        }
        return movieList;
    }

    public static void createShowsForNewMovieInTheater(List<Theater>theaterList,
                                                        List<Movie> movieList ){
        for( Theater currTheater : theaterList ){
            for(Movie currMovie : movieList ){
                Show currShow = new Show(currMovie, currTheater.getCapacity());
                currTheater.getShowList().add(currShow);
            }
        }
        System.out.println("🎉🎉🎉 Successfully added movie into theaters");
    }
}
