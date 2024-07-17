package controller;

import model.Lift;
import repository.LiftRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignLiftController {
    public static void assignLift(){
        List<Lift> allLifts = LiftRepository.getAllLifts();
        UserInputOutputService.printLifts(allLifts);

        int startingPoint = UserInputOutputService.getStartingPoint();
        int destinationPoint = UserInputOutputService.getDestinationPoint();

        List<Lift> eligibleLifts = getEligibleLifts(startingPoint, destinationPoint);
        sortLiftsDependingDependingOnTravelDistance(eligibleLifts, startingPoint, destinationPoint);

        UserInputOutputService.printMessageAndBlankLine("✅--- " + eligibleLifts.get(0).getLiftNumber()+
                " is utilized to move from " + startingPoint + " to " + destinationPoint +" ---✅");
        updateLiftStatus(eligibleLifts.get(0), destinationPoint);

        UserInputOutputService.printLifts(allLifts);
    }

    private static List<Lift> getEligibleLifts( int startingPoint, int destinationPoint ){
        List<Lift> allLifts = LiftRepository.getAllLifts();
        List<Lift> eligibleLifts = new ArrayList<>();

        for( Lift currLift : allLifts ){
            if( currLift.getStopsSet().contains(startingPoint)
                    && currLift.getStopsSet().contains(destinationPoint)){
                eligibleLifts.add(currLift);
            }
        }
        return eligibleLifts;
    }

    private static void sortLiftsDependingDependingOnTravelDistance( List<Lift> eligibleLifts,
                                                                     int startingPoint,
                                                                     int destinationPoint){

        Collections.sort(eligibleLifts, (lift_A ,lift_B)->{
            int a_DistanceToReachPassenger = getDistance(lift_A.getAvailableAt(), startingPoint, lift_A);
            int a_DistanceToReachDestinationFromPassenger = getDistance(startingPoint, destinationPoint, lift_A);
            int a_TotalDistance = a_DistanceToReachPassenger + a_DistanceToReachDestinationFromPassenger;

            int b_DistanceToReachPassenger = getDistance(lift_B.getAvailableAt(), startingPoint, lift_B);
            int b_DistanceToReachDestinationFromPassenger = getDistance(startingPoint, destinationPoint, lift_B);
            int b_TotalDistance = b_DistanceToReachPassenger + b_DistanceToReachDestinationFromPassenger;


            if( a_TotalDistance == b_TotalDistance ){
                String travelDirection = ( startingPoint < destinationPoint ) ? "UP" : "DOWN";
                if( lift_B.getLiftDirection().equals(travelDirection) ){
                    return 1;
                }else{
                    return 0;
                }
            }
            return a_TotalDistance - b_TotalDistance;
        });

    }

    private static int getDistance( int start, int destination, Lift lift ){
        int stops = 0;
        if( start > destination ){
            int temp = start;
            start = destination;
            destination = temp;
        }

        for( int i = start +1; i <= destination; i++ ){ // start +1, current floor is not counted :
            if( lift.getStopsSet().contains(i) ){
                ++stops;
            }
        }
        return stops;
    }

    private static void updateLiftStatus( Lift lift, int destinationPoint ){
        lift.setPrevStop(lift.getAvailableAt());
        lift.setAvailableAt( destinationPoint );

        if( lift.getLiftTravelEndPoint() == destinationPoint ){
            lift.setLiftDirection("DOWN");
            UserInputOutputService
                    .printMessageAndBlankLine("✅---Lift directions changed, lift will move DOWN---✅");
        }else if( lift.getLiftTravelStartPoint() == destinationPoint ){
            lift.setLiftDirection("UP");
            UserInputOutputService
                    .printMessageAndBlankLine("✅---Lift directions changed, lift will move UP---✅");
        }
    }
}
