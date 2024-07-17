package service;

import model.Ticket;
import model.TollBooth;
import model.TollRecord;
import model.Vehicle;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner( System. in);

    public static void closeScanner(){
        scanner.close();
    }

    public static int displayOptions(){
        System.out.println();
        System.out.println("😃---Welcome to Toll Payment System---😃");
        System.out.println("1. Travel from one point from another point");
        System.out.println("2. Display Details of all tolls");
        System.out.println("3. Display details of all vehicles");
        System.out.println("4. Exit");

        return scanner.nextInt();
    }

    public static String getVehicleType(){
        System.out.println();
        System.out.println("Please enter the vehicle Type");
        return scanner.next();

    }

    public static void printMessageAndOneLine( String message ){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static int getVipStatus(){
        System.out.println();
        System.out.println("Are you a VIP ? ");
        System.out.println("1.Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }

    public static int getStartingDestination(){
        System.out.println();
        System.out.println("Please enter the starting point");

        return scanner.nextInt();
    }

    public static int getDestinationPoint() {
        System.out.println();
        System.out.println("Please enter the destination point ");

        return scanner.nextInt();
    }

    public static void printTollAndPayments( List<Ticket> tickets ){
        if( tickets.size() > 0 ){
            printMessageAndOneLine("⚠️---Printing Tickets---⚠️");
            System.out.printf("%-5s| %-5s| %n", "Toll", "Paid");
            System.out.println("--------------");

            for( Ticket ticket : tickets ){
                System.out.println(ticket.getTollAndPaymentDetails());
            }
        }else{
            printMessageAndOneLine("⚠️---You didn't pay any amount to toll⚠️");
        }
    }

    public static void printVehicleTickets( List<Vehicle> vehicles){
        if( vehicles.size() > 0 ){
            printMessageAndOneLine("⚠️---Printing Tickets---⚠️");
            System.out.printf("%-15s| %-7s| %-5s| %-3s| %-5s %n", "Vehicle Number", "Toll", "From", "To", "Paid");
            System.out.println("--------------------------------------------------");

            for(Vehicle vehicle : vehicles ){
                for( Ticket ticket : vehicle.getTicketList()){
                    System.out.println(ticket);
                }
            }
        }
    }

    public static void printAllTollRecords( List<TollBooth> tollBooths ){
        printMessageAndOneLine("⚠️---Printing Toll Records---⚠️");
        System.out.printf("%-5s| %-15s| %-15s| %-5s| %n", "Toll", "Vehicle Number", "Vehicle Type", "Paid");
        System.out.println("--------------------------------------------------");

        for( TollBooth tollBooth : tollBooths ){
            for(TollRecord tollRecord : tollBooth.getTollRecordList()){
                System.out.println(tollRecord);
            }
        }
    }
}
