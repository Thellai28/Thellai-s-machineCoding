package controller;

import model.Seat;
import repository.RailwayReservationRepository;
import service.UserInputOutputService;

import java.util.List;

public class ChartHandler {

    public static void printChart(){
        List<Seat>ls = RailwayReservationRepository.railwayReservationSystem.getSeatList();

        UserInputOutputService.chartPrinter(ls);

    }
}
