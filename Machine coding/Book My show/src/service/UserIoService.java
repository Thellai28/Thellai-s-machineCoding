package service;

import model.Theater;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserIoService {
    private static Scanner scanner = new Scanner(System.in);

    public static int displayOptionsAndGetChoice(){
        System.out.println("😃-----Welcome to BookMyShow---------😃");
        System.out.println("1. Book a movie");
        System.out.println("2. Add movie");
        System.out.println("3. Cancel a movie");
        System.out.println("4. Exit");

        return scanner.nextInt();
    }

    public static int displayTheaterNamesAndGetResponse( List<Theater> theaterList ){
        System.out.println("Please select the theater");
        for( int i = 0; i < theaterList.size(); i++ ){
            Theater theater = theaterList.get(i);
            System.out.println(i+1 +". " + theater.getName());
        }
        return scanner.nextInt()-1; // converting 1 based to 0 based index :
    }

    public static int displayListAndGetResponse( List<String> list ){
        System.out.println("Please select one option from below list");
        for( int i = 0; i < list.size(); i++ ){
            System.out.println(i+1 +". " + list.get(i) );
        }
        return scanner.nextInt() -1; // converting 1 based to 0 based index :
    }

    public static int getRequiredSeatQuantityFromUser(){
        System.out.println("How many seats you are going to book?");
        return scanner.nextInt();
    }

    public static void closeScanner(){
        scanner.close();
    }

    public static String getUserName() {
        System.out.println("Please enter your name");
        return scanner.next();
    }

    public static String getMovieNameFromUser(){
        System.out.println("Please enter the movie name");
        return scanner.next();
    }

    public static List<String> getSupportedLanguagesListFromUser() {
        System.out.println("How many Languages are supported in this movie ?");
        int count = scanner.nextInt();
        List<String> lanugageList = new ArrayList<>();

        for( int i = 0; i < count; i++ ){
            System.out.println("Please enter language ❗");
            lanugageList.add( scanner.next());
        }
        return lanugageList;
    }

    public static int getTicketIdFromUser() {
        System.out.println("Pleaser enter your booked ticket id");
        return scanner.nextInt();
    }

    public static void printTicketDetails( Ticket ticket ){
        System.out.println("Booking confirmation Message");
        System.out.println("Your ticket id is " + ticket.getId());
        System.out.println("Your Show name is " + ticket.getShow().getMovie().getName());
        System.out.println("Your preferred language is " + ticket.getShow().getMovie().getLanguage());
        System.out.println("Total Number of seats booked is " + ticket.getSeatsBooked());
        System.out.println("Enjoy the show 😃😃");
    }

    public static void printHorizontalLines(){
        System.out.println();
        System.out.println("------------------------------------------------------------------");
        System.out.println();
    }
}
