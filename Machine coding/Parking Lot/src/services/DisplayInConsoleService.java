package services;

import models.Vehicle;

import java.util.Scanner;

public class DisplayInConsoleService {
    private static Scanner scanner = new Scanner( System.in);

    public static Vehicle getVehicleDetails(){
        System.out.println("Please enter your vehicle Number");
        String number = scanner.next();

        System.out.println("please enter your vehicle type");
        String type = scanner.next();

        System.out.println("Please enter you vehicle color");
        String color = scanner.next();

        return new Vehicle(color, number, type);
    }

    public static int getTicketNumber(){
        System.out.println("Please enter your ticket number");
        return scanner.nextInt();
    }

    public static void printAvailableSlots(int floorNo, int count, String vehicleType ){
        System.out.println("Floor No: " + floorNo + ", " + vehicleType +" available count = " + count);
    }

    public static void printHorizontalLine(){
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static ParkingLotService getValuesAndInitializeParkingLot(){
        System.out.println("Welcome to ZOHO's parking lot, please provide the following details ");
        System.out.println("Number of floors");
        int floors = scanner.nextInt();

        System.out.println("Bike Slots allowed per floor");
        int bikeSlotsAllowed = scanner.nextInt();

        System.out.println("Car Slots allowed per floor");
        int carSlotsAllowed = scanner.nextInt();

        System.out.println("Bus Slots allowed per floor");
        int busSlotsAllowed = scanner.nextInt();

        return new ParkingLotService(floors, carSlotsAllowed, bikeSlotsAllowed, busSlotsAllowed);
    }

    public static int displayChoicesAndGetResponse(){
        String message = "\n 1. Park Vehicle \n 2. UnPark vehicle \n 3. show all available slots " +
                "\n 4. show particular vehicle available slots \n 5. Exit";
        System.out.println(message);
        return scanner.nextInt();
    }

    public static void closeScanner(){
        scanner.close();
    }


}
