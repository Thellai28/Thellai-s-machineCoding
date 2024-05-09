package repository;

import model.Taxi;
import model.TaxiManagementSystem;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TaxiManagementSystemRepository {
    private static TaxiManagementSystem taxiManagementSystem;

    public static void initializeTaxiManagementSystem(){
        taxiManagementSystem = new TaxiManagementSystem();
    }

    public static List<Taxi> getAllTaxi(){
        List<Taxi> taxiList = new ArrayList<>();

        for( Taxi  taxi : taxiManagementSystem.getTaxiMap().values()){
            taxiList.add( taxi );
        }
        return  taxiList;
    }

    public static void addTicketIntoTicketMap( Ticket ticket ){
        taxiManagementSystem.getTicketMap().put(ticket.getTicketNumber(), ticket);
    }

    public static List<Ticket> getAllTickets(){
        List<Ticket> tickets = new ArrayList<>();
        for( Ticket ticket : taxiManagementSystem.getTicketMap().values()){
            tickets.add(ticket);
        }
        return tickets;
    }
}
