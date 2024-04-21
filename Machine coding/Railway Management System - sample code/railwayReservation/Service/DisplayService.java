package railwayReservation.Service;

import railwayReservation.model.Passenger;

import static railwayReservation.Service.TicketBookingService.*;

public class DisplayService {
    public static void  seatAvailability(){
        System.out.println("Check out the no of seats available");
        System.out.println("Upper Berth "+(berthLimit - upperList.size()));
        System.out.println("Middle Berth "+(berthLimit - middleList.size()));
        System.out.println("Lower Berth "+(berthLimit - lowerList.size()));
    }

    public static void displayConfirmed()
    {
        System.out.println("-------------------------");
        for(Passenger p : confirmationList)
        {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }

    public static void displayRAC()
    {
        System.out.println("-------------------------");
        for(Passenger p : racQueue)
        {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }

    public static void displayWaiting()
    {
        System.out.println("-------------------------");
        for(Passenger p : waitingQueue)
        {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }
}
