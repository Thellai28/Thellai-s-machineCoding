package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookMyShow {
    private Map<String, Theater> theaterMap;
    private Map<Integer, Ticket> ticketMap;


    //--------------------< Constructor & helper methods >-------------------------------------
    public BookMyShow() {
        this.theaterMap = new HashMap<>();
        this.ticketMap = new HashMap<>();

        List<Theater> theaterList = createTheaters();
        createShowsForTheaters(theaterList);
        fillTheaterMap(theaterList);
    }


    private List<Theater> createTheaters(){
        final String theaterNames[] = {"Albert", "Rohini", "PVR"};
        final int capacityOfTheaters[] = {20, 25, 50};
        List<Theater> theaterList = new ArrayList<>();

        for( int i = 0; i < theaterNames.length; i++ ){
            Theater theater = new Theater( capacityOfTheaters[i], theaterNames[i]);
            theaterList.add( theater );
        }
        return theaterList;
    }



    private void createShowsForTheaters(List<Theater> theaterList){
        final String movieNames[] = {"Iron man", "Avatar", "Pacific Rim"};
        final String languages[] = {"Tamil", "English", "Hindi", "Malayalam"};

        for( Theater currentTheater : theaterList ){
            List<Show>showList = new ArrayList<>();

            for( int movieIdx = 0; movieIdx < movieNames.length; movieIdx++ ){

                for( int languageIdx = 0; languageIdx < languages.length; languageIdx++ ){ // Every movie will support every language :
                    Movie movie = new Movie(languages[languageIdx], movieNames[movieIdx]);
                    Show show = new Show( movie, currentTheater.getCapacity());
                    showList.add(show);
                }
            }
            currentTheater.setShowList(showList);
        }
    }


    private void fillTheaterMap( List<Theater> theaterList ){
        for( Theater theater  : theaterList ){
            this.theaterMap.put(theater.getName(), theater);
        }
    }

    //------------------< Getters & setter >---------------------------------------------

    public Map<String, Theater> getTheaterMap() {
        return theaterMap;
    }

    public void setTheaterMap( Map<String, Theater> theaterMap ) {
        this.theaterMap = theaterMap;
    }

    public Map<Integer, Ticket> getTicketMap() {
        return ticketMap;
    }

    public void setTicketMap( Map<Integer, Ticket> ticketMap ) {
        this.ticketMap = ticketMap;
    }
}
