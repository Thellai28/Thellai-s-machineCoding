package model;

import java.util.HashMap;
import java.util.Map;

public class SplitWise {
    private  Map<String, User> userObjectsMap;
    private  Map<String, String> userPasswordMap;
    private  Map<String, Group> groupObjectsMap;

    public SplitWise() {
        this.userObjectsMap = new HashMap<>();
        this.userPasswordMap = new HashMap<>();
        this.groupObjectsMap = new HashMap<>();

        fillUserObjectsMap();
        fillUserPasswordMap();
    }

    private void fillUserObjectsMap(){
        userObjectsMap.put("Thellai", new User("Thellai") );
        userObjectsMap.put("Sanjay", new User("Sanjay") );
        userObjectsMap.put("Prem", new User("Prem") );
    }

    private void fillUserPasswordMap(){
        userPasswordMap.put("Thellai", "Password" );
        userPasswordMap.put("Sanjay", "Password" );
        userPasswordMap.put("Prem", "Password" );
    }

    //----------< Getters >------------------

    public Map<String, Group> getGroupObjectsMap() {
        return groupObjectsMap;
    }

    public Map<String, User> getUserObjectsMap() {
        return userObjectsMap;
    }

    public Map<String, String> getUserPasswordMap() {
        return userPasswordMap;
    }
}
