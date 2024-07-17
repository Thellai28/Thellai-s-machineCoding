package controller;

import model.TollBooth;
import model.TollRecord;
import repository.TollPaymentSystemRepository;
import service.UserInputOutputService;

import java.util.Map;

public class PrintTollsController {
    public static void printAllTollRecords(){
        Map<Integer, TollBooth> tollMap = TollPaymentSystemRepository.getAllTolls();
        UserInputOutputService.printAllTollRecords(tollMap.values().stream().toList());
    }
}
