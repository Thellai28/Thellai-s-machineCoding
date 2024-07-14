package repository;

import model.RailwayReservationSystem;
import service.UserInputOutputService;

public class RailwayReservationRepository {
    public  static RailwayReservationSystem railwayReservationSystem;

    public static void initializeRailwayReservation(){
        railwayReservationSystem = new RailwayReservationSystem();
        UserInputOutputService
                .printMessageAndOneLine("✅-- Successfully initialized railway reservation system");
    }
}
