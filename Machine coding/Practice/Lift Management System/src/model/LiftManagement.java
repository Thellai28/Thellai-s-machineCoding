package model;

import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class LiftManagement {
    private List<Lift> liftList;

    public LiftManagement() {
        this.liftList = new ArrayList<>();
        generateLiftsAndAddIntoLiftList();
    }

    private void generateLiftsAndAddIntoLiftList(){
        UserInputOutputService.printMessageAndBlankLine("✅---Generating Lifts---✅");

        int stops[][] = {
                            {0, 1, 2, 3, 4, 5},
                            {0, 1, 2, 3, 4, 5},
                            {0, 6, 7, 8, 9, 10},
                            {0, 6, 7, 8, 9, 10},
                            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
                        };

        for( int i = 0; i < 5; i++ ){
            Lift lift = new Lift();

            for( int stop : stops[i] ){
                lift.getStopsSet().add(stop);

                if(lift.getLiftTravelStartPoint() == -1 ){
                    lift.setLiftTravelStartPoint(stop);
                    // First value in array will be stored as start point :
                }

                lift.setLiftTravelEndPoint(stop); // last value in array will be stored finally :
            }
            liftList.add(lift);
        }
    }

    //-------------------------< Getters & Setters >---------------------------------


    public List<Lift> getLiftList() {
        return liftList;
    }
}
