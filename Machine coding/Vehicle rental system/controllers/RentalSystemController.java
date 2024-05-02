package controllers;

import models.BookingStatusEnum;
import models.Ticket;
import models.Vehicle;
import services.RentalSystemService;
import services.UserIoService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static services.UserIoService.*;

public class RentalSystemController {

    public static void displayAllAvailableVehicles(){

        List<Vehicle> AvailableVehicleList = RentalSystemService.allAvailableVehicles(getVehicleType());
        if( AvailableVehicleList.size() > 0 ){
            displayVehicleList(AvailableVehicleList);
        }else {
            System.out.println("😓 ERROR MESSAGE : No Vehicles available, Please come after sometime");
            UserIoService.printHorizontalLines();
        }
    }



    public static void bookVehicle(){

        List<Vehicle> AvailableVehicleList = RentalSystemService.allAvailableVehicles(getVehicleType());

        if( AvailableVehicleList.size() > 0 ){
            displayVehicleList(AvailableVehicleList);
            int choice = askUserToSelectVehicle() -1;

            if( choice >= 0 && choice < AvailableVehicleList.size() ){
                Vehicle selectedVehicle = AvailableVehicleList.get(choice);
                RentalSystemService.bookVehicle(selectedVehicle);
                Ticket ticket = new Ticket(getOwnerDetails(), selectedVehicle);
                RentalSystemService.saveTicketsIntoMap(ticket.getTicketId(), ticket);
                System.out.println("😁 SUCCESS MESSAGE : Vehicle booked successfully, you ticket id is " + ticket.getTicketId() +" .");
                UserIoService.printHorizontalLines();
            }else {
                if( Main.isRentalServiceActive ){
                    System.out.println("😓 ERROR MESSAGE : Please choose a valid vehicle");
                    UserIoService.printHorizontalLines();
                    bookVehicle(); // recursive call :
                }
            }

        }else{
            System.out.println("😓 ERROR MESSAGE : Sorry, All vehicles are booked,come back later");
            printHorizontalLines();
        }
    }



    public static void dropVehicle(){
        int ticketNumber = getTicketIdFromUser();
        Ticket ticket = RentalSystemService.fetchTicketFromMap(ticketNumber);

        if(  ticket != null ) {
            ticket.getVehicle().setBookingStatus(BookingStatusEnum.AVAILABLE);
            Date checkInTime = ticket.getCheckInTime();
            Date checkOutTime = new Date();

            long durationMillis = checkOutTime.getTime() - checkInTime.getTime();
            long durationHours = TimeUnit.MILLISECONDS.toHours(durationMillis);
            durationHours = (durationHours < 1L) ? 1 : durationMillis;

            double vehicleRent = durationHours * ticket.getVehicle().getRentPerHour();
            System.out.println("😁 SUCCESS MESSAGE : Vehicle parked successfully, you rental charge is " + vehicleRent +" $");

            UserIoService.printHorizontalLines();
        }else {
            if( Main.isRentalServiceActive ){
                System.out.println("Enter a valid ticket number ");
                UserIoService.printHorizontalLines();
                dropVehicle();; // recursive call :
            }
        }
    }



    public static void cancelVehicle(){
        int ticketNumber = getTicketIdFromUser();
        Ticket ticket = RentalSystemService.fetchTicketFromMap(ticketNumber);
        if(  ticket != null ) {
            ticket.getVehicle().setBookingStatus(BookingStatusEnum.AVAILABLE);
            System.out.println("😁 SUCCESS MESSAGE : Vehicle Cancelled successfully, Please visit again");
            UserIoService.printHorizontalLines();
        }else {
            if( Main.isRentalServiceActive ){
                System.out.println("😓 ERROR MESSAGE : Enter a valid ticket number ");
                UserIoService.printHorizontalLines();
                cancelVehicle(); // recursive call :
            }
        }
    }

}
