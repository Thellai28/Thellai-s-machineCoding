package service;

import model.Seat;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner(System.in);

    public static void closeScanner(){
        scanner.close();
    }

    public static int displayHomePageAndGetResponse(){
        System.out.println("😃--------- Welcome to railway reservation system--------------😃");
        System.out.println("1. Booking");
        System.out.println("2. Cancel Booking");
        System.out.println("3. Print Booked tickets");
        System.out.println("4. Print available seats");
        System.out.println("5. Exit");

        return scanner.nextInt();
    }

    public static String getUserName(){
        System.out.println("Please enter your name");
        return scanner.next();
    }

    public static int getUserAge(){
        System.out.println("Please enter your age");
        return scanner.nextInt();
    }

    public static String getUserGender(){
        System.out.println("Please enter your gender");
        return scanner.next();
    }

    public static String getBerthPreference(){
        System.out.println("Please enter your berth preference ( 'LOWER', 'MIDDLE', 'UPPER')");
        return scanner.next();
    }

    public static boolean getChildrenInformation(){
        System.out.println("Do you have children ('Enter YES or NO')");
        String response = scanner.next();
        return response.equalsIgnoreCase("YES");
    }

    public static int displayListContentAndGetResponse( List<String> availableBerths ){
        for( int i = 0; i < availableBerths.size(); i++ ){
            System.out.println(i+1 + ". " + availableBerths.get(i) );
        }
        return scanner.nextInt()-1;
    }

    public static void printTicketConfirmationMessage( Ticket ticket ){
        System.out.println("🎉🎉🎉 Your ticket reservation is successful...");
        System.out.println("Name : " + ticket.getBookedUser().getName());
        System.out.println("Age : " + ticket.getBookedUser().getAge());
        System.out.println("Gender : " + ticket.getBookedUser().getGender());
        System.out.println("Ticket number :" + ticket.getTicketNumber());
        System.out.println("Booked seat number : " + ticket.getBookedSeat().getSeatNumber());
        System.out.println("Berth type : " + ticket.getBookedSeat().getBerth());

        printHorizontalLines();
    }

    public static void printHorizontalLines(){
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
    }

    public static int getTicketNumber(){
        System.out.println("Please enter your ticket number ");
        return scanner.nextInt();
    }

    public static void printAllTickets( List<Ticket> ticketList ){
        System.out.println("⚠️ Printing all booked tickets ⚠️");
        for (Ticket ticket : ticketList ){
            System.out.println("Ticket Number : " + ticket.getTicketNumber());
            System.out.println("User name : " + ticket.getBookedUser().getName());
            System.out.println("User age : " + ticket.getBookedUser().getAge());
            System.out.println("Booked seat number : " + ticket.getBookedSeat().getSeatNumber());
            System.out.println("Booked seat berth : " + ticket.getBookedSeat().getBerth());

            printHorizontalLines();
        }
    }

    public static void printAllAvailableSeats(List<Seat> seatList){
        System.out.println("⚠️ Printing All available Seats ⚠️");

        for( Seat seat : seatList ){
            System.out.println("Seat Number : " + seat.getSeatNumber());
            System.out.println("Berth : " + seat.getBerth());

            printHorizontalLines();
        }
    }

    public static void printMessageAndAddOneBlankLine( String message ){
        System.out.println(message);
        System.out.println();
    }
    
}
