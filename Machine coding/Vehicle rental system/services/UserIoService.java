package services;

import models.Vehicle;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class UserIoService {
    private static Scanner scanner = new Scanner( System.in );
    public static int  displayAndGetUserChoice(){
        System.out.println("<-------🎊🎊 Vehicle Rental Shop 🎊🎊 ------------>");
        System.out.println("1. Show all available vehicles");
        System.out.println("2. Book a vehicle ");
        System.out.println("3. Drop a vehicle ");
        System.out.println("4. Cancel a booking");
        System.out.println("5. Exit");

        return scanner.nextInt();
    }

    public static String  getVehicleType(){
        System.out.println("Enter Your preferred vehicle Type");
        return scanner.next();
    }

    public static void displayVehicleList( List<Vehicle> vehicleList ){
        System.out.println("Available vehicle List");
        for( int i = 0; i < vehicleList.size(); i++ ){
            Vehicle currVehicle = vehicleList.get(i);
            System.out.println(i+1+" " + currVehicle.getNumber() + " at platform no : " +currVehicle.getPlatformNumber() + " .");
        }
        printHorizontalLines();
    }

    public static int askUserToSelectVehicle(){
        System.out.println("Choose a vehicle from the above shown list and enter number");
        return scanner.nextInt();
    }

    public static String getOwnerDetails(){
        System.out.println("Please enter Your name");
        return scanner.next();
    }

    public static int getTicketIdFromUser(){
        System.out.println("Please enter your ticket number");
        return scanner.nextInt();
    }

    public static void closeScanner(){
        scanner.close();
    }

    public static void printRemainingTime(){
        String remainingTime =  TimerService.getFormattedRemainingTime();
        System.out.println("⌛REMAINING TIME : " + remainingTime);
    }

    public static void printHorizontalLines(){
        System.out.println();
        printRemainingTime();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }

}
