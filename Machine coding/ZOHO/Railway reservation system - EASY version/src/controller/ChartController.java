package controller;

import model.ChartEntity;
import model.Ticket;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class ChartController {
    public static void createChart(){
        List<Ticket>validTickets = getAllValidTickets();
        List<ChartEntity>chartList = createChartForTickets(validTickets);
        UserInputOutputService.printChart(chartList);
    }

    private static List<Ticket> getAllValidTickets(){
        List<Ticket> tickets = new ArrayList<>();

        for( Ticket ticket : RailwayReservationRepository.getAllTicketsFromMap() ){
            if( ticket.getStatus().equals("VALID")){
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    private static List<ChartEntity> createChartForTickets( List<Ticket> ticketList ){
        List<ChartEntity>chartList = new ArrayList<>();

        for( Ticket ticket  : ticketList ){
            chartList.add( new ChartEntity(ticket) );
        }
        return chartList;
    }
}
