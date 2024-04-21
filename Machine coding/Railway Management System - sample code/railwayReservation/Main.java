package railwayReservation;

import railwayReservation.Service.DisplayService;
import railwayReservation.Service.TicketBookingService;
import railwayReservation.Service.TicketCancelingService;
import railwayReservation.model.Passenger;

import java.util.Scanner;

public class Main {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;

        while( isActive ){
            System.out.println("\nChoose any one \n1.Book ticket " +
                    "\n2.Cancel ticket \n3. Display confirmed list" +
                    "\n4.Display RAC list \n5.Display waiting list \n6. Exit");
            int response = scanner.nextInt();

            switch (response ){
                case 1:{
                    System.out.println("Enter name : ");
                    String name = scanner.next();
                    System.out.println("Enter age ");
                    int age = scanner.nextInt();
                    System.out.println("Enter berth : ");
                    char preference = scanner.next().charAt(0);

                    if( preference == 'U' || preference == 'u' || preference == 'M'
                            || preference == 'm' || preference =='L' || preference == 'l'){
                        TicketBookingService.bookTicket( new Passenger(age ,name, preference));
                    }else System.out.println("Invalid input! ");
                    break;
                } case 2 : {
                    System.out.println("Enter your ticket ID : ");
                    int ticketId = scanner.nextInt();
                    System.out.println(TicketCancelingService.cancelling(ticketId));
                    break;
                } case 3 : {
                    DisplayService.displayConfirmed();
                    break;
                } case 4 : {
                    DisplayService.displayRAC();
                    break;
                } case 5: {
                    DisplayService.displayWaiting();
                    break;
                } case 6: {
                    System.out.println("\n Thank you, have a nice day !");
                    scanner.close();
                    isActive = false;
                    break;
                }
            }
        }
    }
}
