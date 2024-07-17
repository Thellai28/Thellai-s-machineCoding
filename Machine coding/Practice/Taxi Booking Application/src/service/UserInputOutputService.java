package service;

import model.Taxi;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner( System.in );

    public  static void closeScanner(){
        scanner.close();
    }

    public static int displayGreetingOptionsAndGetResponse(){
        System.out.println("😃--- Welcome to taxi Management System---😃");
        System.out.println("Please choose one option from the below");
        System.out.println("1. Book a taxi ");
        System.out.println("2. Print taxi Details");
        System.out.println("3. Exit");

        return scanner.nextInt();
    }

    public static int getCustomerIdFromUser(){
        System.out.println("Please enter Your customer Id");
        return scanner.nextInt();
    }

    public static char getPickupPointFromUser(){
        System.out.println("Please enter your pick up point");
        return scanner.next().charAt(0);
    }

    public static int getPickUpTimeFromUser(){
        System.out.println("Please enter your pickup Time " );
        return scanner.nextInt();
    }

    public static char getDestinationPointFromUser(){
        System.out.println("Please enter your destination point ");
        return scanner.next().charAt(0);
    }

    public static void printTicket( Ticket ticket ){
        System.out.println("--------Printing ticket---------------");
        System.out.println("Ticket number is " + ticket.getTicketNumber());
        System.out.println("Pick Up Point : " + ticket.getPickUpPoint());
        System.out.println("Destination Point : " + ticket.getDestinationPoint());
        System.out.println("Pick Up time : " + ticket.getPickUpTime());
        System.out.println("Drop Time : " + ticket.getDropTime());
        System.out.println("Total travel expense : " + ticket.getTravelCharges());

        printHorizontalLines();
    }

    public static void printHorizontalLines(){
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    public static void printTaxiDetails( List<Ticket> ticketList){
        System.out.println("Printing taxi details");
        for( Ticket ticket : ticketList){
            System.out.println("Taxi number : " + ticket.getTaxi().getTaxiNumber());
            System.out.println("Customer Id : " + ticket.getCustomerId());
            System.out.println("Pick Up Point : " + ticket.getPickUpPoint());
            System.out.println("Destination Point : " + ticket.getDestinationPoint());
            System.out.println("PickUp TIme : " + ticket.getPickUpTime());
            System.out.println("Drop Time : " + ticket.getDropTime());
            System.out.println("Total Earning : " + ticket.getTravelCharges());
            System.out.println("---------------------------------------------------");
        }
    }
}
