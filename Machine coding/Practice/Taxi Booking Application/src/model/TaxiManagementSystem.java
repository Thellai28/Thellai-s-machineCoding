package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxiManagementSystem {
    private Map<Integer, Taxi> taxiMap;
    private Map<Integer, Ticket> ticketMap;

    public TaxiManagementSystem() {
        this.taxiMap = new HashMap<>();
        this.ticketMap = new HashMap<>();

        List<Taxi> taxiList = createTaxi();
        addTaxisIntoMap(taxiList);
    }

    private List<Taxi> createTaxi(){
        List<Taxi> taxiList = new ArrayList<>();
        for( int i = 0; i < 2; i++ ){
            Taxi taxi  =  new Taxi();
            taxiList.add( taxi );
        }
        return taxiList;
    }
    private void addTaxisIntoMap( List<Taxi> taxiList ){
        for( Taxi taxi : taxiList ){
            taxiMap.put( taxi.getTaxiNumber(), taxi);
        }
    }

    //----------------< getters & setters >-----------------------------

    public Map<Integer, Taxi> getTaxiMap() {
        return taxiMap;
    }

    public void setTaxiMap( Map<Integer, Taxi> taxiMap ) {
        this.taxiMap = taxiMap;
    }

    public Map<Integer, Ticket> getTicketMap() {
        return ticketMap;
    }

    public void setTicketMap( Map<Integer, Ticket> ticketMap ) {
        this.ticketMap = ticketMap;
    }
}
