package service;

import java.util.Random;

public class DiceService {
    private static Random random = new Random();

    public static int rollDice(){
        return random.nextInt(6)+1;
        // return Random.nextInt(6)+1;
    }
}
