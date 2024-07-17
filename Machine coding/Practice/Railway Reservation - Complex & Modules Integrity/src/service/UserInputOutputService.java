package service;

import model.Seat;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner(System.in);

    public static int displayMenu(){
        printMessageAndOneLine("😀-- Welcome to railway reservation system --😀");
        System.out.println("1. Book ticket");
        System.out.println("2. Cancel ticket");
        System.out.println("3. Ticket summary");
        System.out.println("4. Chart");
        System.out.println("5. Exit");

        return scanner.nextInt();
    }

    public static char getStation( String message ){
        printMessageAndOneLine(message);
        return scanner.next().charAt(0);
    }

    public static int getTotalSeatRequired(){
        printMessageAndOneLine("😀-- Please enter the total seats required ");
        return scanner.nextInt();
    }

    public static void printMessageAndOneLine( String message ){
        System.out.println();
        System.out.println(message);
    }

    public static int getTicketId(){
        printMessageAndOneLine("😀-- please enter your ticket id");
        return scanner.nextInt();
    }

    public static int getCancelCount(){
        printMessageAndOneLine("😀-- Please enter cancel count");
        return scanner.nextInt();
    }

    public static void printTicket( Ticket ticket){
        printMessageAndOneLine("✅-- Ticket booked successfully");
        System.out.println("Ticket id : " + ticket.getPnr());
        System.out.println("From : " + ticket.getSource());
        System.out.println("To : " + ticket.getDestination());
        System.out.println("Total Number of tickets : " + ticket.getSeatsBooked().size());
    }

    public static void printTicketSummary( Ticket ticket ){
        printMessageAndOneLine("✅-- Printing ticket summary!");
        System.out.println("Ticket id : " + ticket.getPnr());
        System.out.println("Source : " + ticket.getSource());
        System.out.println("Destination : " + ticket.getDestination());
        System.out.println("Number of tickets booked : " + ticket.getSeatsBooked().size());

    }

    public static void chartPrinter( List<Seat>seatList ){
        printMessageAndOneLine("✅-- Printing chart !!");
        System.out.printf("%-2s| %-2s| %-2s| %-2s| %-2s| %-2s|\n", "N", "A", "B", "C", "D","E");
        System.out.println("-----------------------");
        for( Seat seat : seatList ){
            System.out.println(seat);
        }
    }
}
