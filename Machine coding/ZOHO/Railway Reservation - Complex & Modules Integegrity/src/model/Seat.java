package model;

import service.UserInputOutputService;

public class Seat {
    private static int seatIdGenerator = 1;
    private int seatId;
    private boolean availableStops[];

    public Seat( int size ) {
        this.availableStops = new boolean[size];
        this.seatId = seatIdGenerator++;
        fillSeats(size);
        UserInputOutputService.printMessageAndOneLine("✅-- seat created successfully");
    }


    @Override
    public String toString() {
        String formattedString = String.format("%-2d| %-2s| %-2s| %-2s| %-2s| %-2s|",
                seatId, fillStar(availableStops[1]), fillStar(availableStops[2]),
                fillStar(availableStops[3]), fillStar(availableStops[4]),
                fillStar(availableStops[5]) );
        return formattedString;
    }


    private static String fillStar( boolean bool ){
        return ( bool == false )?"*" : " ";
    }

    private void fillSeats( int size){
        for( int i = 0; i < size; i++ ){
            this.availableStops[i] = true;
        }
    }



    public boolean[] getAvailableStops() {
        return availableStops;
    }

    public int getSeatId() {
        return seatId;
    }
}
