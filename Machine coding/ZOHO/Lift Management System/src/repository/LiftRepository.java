package repository;

import model.Lift;
import model.LiftManagement;

import java.util.List;

public class LiftRepository {
    private static LiftManagement liftManagement;

    public static void initializeLiftRepository(){
        liftManagement = new LiftManagement();
    }

    public static List<Lift> getAllLifts(){
        return liftManagement.getLiftList();
    }
}
