package service;

import model.Status;
import model.Taxi;
import model.Ticket;
import repository.TaxiManagementSystemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingService {

    public static List<Taxi> getAvailableTaxiList(){
        return TaxiManagementSystemRepository.getAllTaxi();
    }

    public static List<Taxi> chooseEligibleTaxisFromTaxiList( List<Taxi> taxiList,
                                                              int pickUpTime ){
        List<Taxi> eligibleTaxiList = new ArrayList<>();

        for(Taxi taxi : taxiList ){
            if( taxi.getStatus().equals(Status.AVAILABLE) || taxi.getAvailableTime() <= pickUpTime){
                eligibleTaxiList.add( taxi );
            }
        }
        return eligibleTaxiList;
    }

    public static List<Taxi> sortTaxiListBasedOnDistance( List<Taxi> eligibleTaxiList, char pickUpPoint ){

        Collections.sort(eligibleTaxiList, (a, b)->{
            int aDistanceFromPickupPoint =  Math.abs((int)pickUpPoint - (int)a.getAvailableLocation());
            int bDistanceFromPickupPoint = Math.abs((int)pickUpPoint - (int)b.getAvailableLocation());

            if( aDistanceFromPickupPoint == bDistanceFromPickupPoint ){
                return a.getTotalMoneyEarned() - b.getTotalMoneyEarned();
            }
            return aDistanceFromPickupPoint - bDistanceFromPickupPoint;
        });
        return eligibleTaxiList;
    }

    private static int calculateTravelExpense( Taxi chosenTaxi, char pickUpPoint,
                                               char destinationPoint ){
        final int INITIAL_DISTANCE = 5;
        int totalDistance = calculateTotalDistance(pickUpPoint, destinationPoint);
        int totalCost = 0;

        if( totalDistance <= INITIAL_DISTANCE ){
            totalCost = chosenTaxi.getINITIAL_5KMS_CHARGE();
        }else{
            int subsequentDistance = totalDistance - INITIAL_DISTANCE;
            totalCost = chosenTaxi.getINITIAL_5KMS_CHARGE() +
                    ( subsequentDistance * chosenTaxi.getSUBSEQUENT_KMS_CHARGE());
        }
        return totalCost;
    }

    private static void  addTravelCostIntoTaxi( Taxi chosenTaxi, int totalTravelCost ){
        chosenTaxi.setTotalMoneyEarned( chosenTaxi.getTotalMoneyEarned() + totalTravelCost );
    }

    public static void bookTaxi( Taxi chosenTaxi, char pickUpPoint, char destinationPoint, int pickUpTime ){
        chosenTaxi.setStatus(Status.BOOKED);
        chosenTaxi.setAvailableLocation(destinationPoint);

        int totalDistance = calculateTotalDistance(pickUpPoint, destinationPoint);
        int totalTime = calculateTotalTimeOfTravel(chosenTaxi, totalDistance);
        int totalTravelCost = calculateTravelExpense(chosenTaxi, pickUpPoint, destinationPoint);

        addTravelCostIntoTaxi(chosenTaxi, totalTravelCost);
        chosenTaxi.setAvailableTime(pickUpTime + totalTime);
    }

    private static int calculateTotalDistance(char pickUpPoint, char destinationPoint ){
        final int DISTANCE_BETWEEN_STOPS = 15;
        return Math.abs((int)destinationPoint - (int)pickUpPoint) *  DISTANCE_BETWEEN_STOPS;
    }


    private static int calculateTotalTimeOfTravel(Taxi chosenTaxi,  int totalDistance ){
        final int DISTANCE_BETWEEN_STOPS = 15;
        int totalStops = totalDistance / DISTANCE_BETWEEN_STOPS;
        return totalStops * chosenTaxi.getPOINT_TO_POINT_TRAVEL_TIME();
    }

    public static Ticket createTicket( char destinationPoint, Taxi chosenTaxi,
                                       char pickupPoint, int pickUpTime, int customerId ){
        int totalCost = calculateTravelExpense(chosenTaxi, pickupPoint, destinationPoint);

        return  new Ticket(destinationPoint, chosenTaxi.getAvailableTime(),
                pickupPoint, pickUpTime, chosenTaxi, totalCost, customerId);
    }

    public static void addTicketIntoTicketMap( Ticket ticket ){
        TaxiManagementSystemRepository.addTicketIntoTicketMap(ticket);
    }

}
