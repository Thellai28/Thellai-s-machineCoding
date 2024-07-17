package service;

import model.ChartEntity;
import model.Seat;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner;

    public static void initializeScanner(){
        scanner = new Scanner(System.in);
        UserInputOutputService
                .printMessageAndSingleLine("✅---Scanner initialized successfully---");
    }
    public static void closeScanner(){
        scanner.close();
        UserInputOutputService
                .printMessageAndSingleLine("✅---Scanner closed successfully---");
    }

    public static void printMessageAndSingleLine( String message ){
        System.out.println();
        System.out.println(message);
    }

    //---------------------------< Display functionalities >-------------------------------------------

    public static int displayMainMenu(){
        printMessageAndSingleLine("😄---Welcome to railway reservation system---😄");
        System.out.println("1. Booking");
        System.out.println("2. Check available seats");
        System.out.println("3. Cancel seat");
        System.out.println("4. Prepare chart");
        System.out.println("5. Exit");

        return scanner.nextInt();
    }

    public static int displaySeatTypes( List<String> seatTypes){
        printMessageAndSingleLine("😄- Please select a seat type");
        for( int i = 0; i < seatTypes.size(); i++ ){
            System.out.println(i+1 + ". " + seatTypes.get(i));
        }
        return scanner.nextInt() -1;
    }

    public static void displaySeats( List<Seat> availableSeats ){
        printMessageAndSingleLine("😄- Displaying available seats");

        for( int i = 0; i < availableSeats.size(); i++ ){
            System.out.println(i+1 + ". " + availableSeats.get(i).getSeatNumber() );
        }
    }

    public static int displaySeatsAndChoose( List<Seat> availableSeats ){
        printMessageAndSingleLine("😄- Displaying available seats");

        for( int i = 0; i < availableSeats.size(); i++ ){
            System.out.println(i+1 + ". " + availableSeats.get(i).getSeatNumber() );
        }
        return scanner.nextInt() -1;
    }

    //---------------< Get values from user >---------------------------
    public static String getUserName(){
        printMessageAndSingleLine("😄- Please enter your user name ");
        return scanner.next();
    }

    public static int getTicketId(){
        printMessageAndSingleLine("😄- Please enter your ticket id ");
        return scanner.nextInt();
    }


    //-----------------< Printing functionalities >------------------
    public static void printTicket( Ticket ticket ){
        printMessageAndSingleLine("✅--- Ticket booked successfully");
        System.out.println("1. Ticket Id : " + ticket.getTicketId());
        System.out.println("2. User name : " + ticket.getPassenger().getName());
        System.out.println("3. Seat number : " + ticket.getSeat().getSeatNumber());
        System.out.println("4. Seat Type : " + ticket.getSeat().getType());
    }

    public static void printChart(List<ChartEntity> chartEntityList) {
        printMessageAndSingleLine("✅---Printing chart---");
        String formattedString = String.format("%-5s| %-20s| %-12s| %-7s|",
                "S.NO", "Name", "Seat number", "Berth");
        System.out.println(formattedString);
        System.out.println("-----------------------------------------------------");

        for (ChartEntity entity : chartEntityList) {
            System.out.println(entity);
        }
    }
}
