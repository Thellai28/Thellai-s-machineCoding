package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TollBooth {
    private static int idGenerator = 2;

    private int tollBoothId;
    private Map<String, Integer>vehicleAndFeeMap;
    private List<TollRecord> tollRecordList;
    private int discountForVip = 20;

    public TollBooth() {
        this.vehicleAndFeeMap = new HashMap<>();
        this.tollBoothId = idGenerator;
        tollRecordList = new ArrayList<>();
        idGenerator += 2; // for every 2 points, there is one toll :
    }

    //----------------------------< Getter & Setter >--------------------------


    public int getDiscountForVip() {
        return discountForVip;
    }

    public void setDiscountForVip( int discountForVip ) {
        this.discountForVip = discountForVip;
    }

    public List<TollRecord> getTollRecordList() {
        return tollRecordList;
    }



    public int getTollBoothId() {
        return tollBoothId;
    }

    public void setTollBoothId( int tollBoothId ) {
        this.tollBoothId = tollBoothId;
    }

    public Map<String, Integer> getVehicleAndFeeMap() {
        return vehicleAndFeeMap;
    }

    public void setVehicleAndFeeMap( Map<String, Integer> vehicleAndFeeMap ) {
        this.vehicleAndFeeMap = vehicleAndFeeMap;
    }
}
