package service;

import model.Passenger;
import model.Seat;
import model.Ticket;
import repository.RailwayReservationSystemRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingService {

    public static Passenger getUserDetails(){

        String name = UserInputOutputService.getUserName();
        int age = UserInputOutputService.getUserAge();
        String gender = UserInputOutputService.getUserGender();

        List<String> availableBerths = getAvailableBerths();
        // if there is no seat available, the default preference will be RAC & bookRacBerth() will take care of this :
        String berthPreference = "RAC";
        if(availableBerths.size() > 0){
            int idx = UserInputOutputService.displayListContentAndGetResponse(availableBerths);
            berthPreference = availableBerths.get(idx);
        }

        return new Passenger(age, berthPreference, gender, name);
    }


    public static void bookLowerBerth( Passenger passenger ){

        if( isEligibleForLowerSeat(passenger) ){
            Seat chosenSeat = RailwayReservationSystemRepository.getPreferredSeat(passenger.getBerthPreference());
            assignUserForSeat(passenger, chosenSeat);
            Ticket ticket = createTicket(passenger, chosenSeat);
            saveTicketIntoTicketMap(ticket);

            UserInputOutputService.printTicketConfirmationMessage(ticket);
        }else{
            String message = "☹️☹️ Sorry Your are not eligible to select LOWER berth, choose another berth";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }


    public static void bookUpperOrMiddleBerth( Passenger passenger ){
        Seat chosenSeat = RailwayReservationSystemRepository.getPreferredSeat(passenger.getBerthPreference());
        assignUserForSeat(passenger, chosenSeat);
        Ticket ticket = createTicket(passenger, chosenSeat);
        saveTicketIntoTicketMap(ticket);

        UserInputOutputService.printTicketConfirmationMessage(ticket);
    }


    public static void bookRacBerth( Passenger passenger ){

        boolean isRacFull =  RailwayReservationSystemRepository.isRacFull();
        if( !isRacFull ){
            Seat racSeat = RailwayReservationSystemRepository.getPreferredSeat(passenger.getBerthPreference());
            if( racSeat != null ){
                assignUserForSeat(passenger, racSeat);
                addUserIntoRacQueue(passenger);

                Ticket ticket = createTicket(passenger, racSeat);
                saveTicketIntoTicketMap(ticket);

                UserInputOutputService.printTicketConfirmationMessage(ticket);
            }else{
                checkAndAddUserIntoWaitingList(passenger);
            }
        }else{
            checkAndAddUserIntoWaitingList(passenger);
        }
    }

    private static void checkAndAddUserIntoWaitingList( Passenger passenger ){
        boolean isWaitingListFull = RailwayReservationSystemRepository.isWaitingListFull();

        if( !isWaitingListFull ){
            addUserIntoWaitingList(passenger);

            String message = "‼️‼️Your preferred seat is not available, so adding " + passenger.getName() + " into waiting list";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }else{

            String message = "☹️☹️ sorry, No seat available for booking ";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }


    private static List<String> getAvailableBerths(){
        Set<String> availableBerths = new HashSet<>();
        for( Seat seat : RailwayReservationSystemRepository.getAllSeats() ){
            if( seat.getUserList().size() < seat.getSeatCapacity()  ){
                availableBerths.add( seat.getBerth() );
            }
        }
        return new ArrayList<>(availableBerths);
    }


    private static boolean isEligibleForLowerSeat( Passenger passenger ){

        if( passenger.getAge() >= 60 ){
            return true;
        }else if ( passenger.getGender().equalsIgnoreCase("FEMALE") ){
            boolean haveChildren = UserInputOutputService.getChildrenInformation();
            if( haveChildren ){
                return true;
            }
        }
        return false;
    }

    private static void assignUserForSeat( Passenger passenger, Seat chosenSeat ){
        chosenSeat.getUserList().add(passenger);
    }
    private static void addUserIntoRacQueue( Passenger passenger ){
        RailwayReservationSystemRepository.addIntoRacQueue(passenger);
    }

    private static void addUserIntoWaitingList( Passenger passenger ){
        RailwayReservationSystemRepository.addIntoWaitingListQueue(passenger);
    }

    private static Ticket createTicket( Passenger passenger, Seat chosenSeatForBooking ){
        return new Ticket(passenger, chosenSeatForBooking);
    }

    private static void saveTicketIntoTicketMap( Ticket ticket ){
        RailwayReservationSystemRepository.addTicketIntoTicketMap(ticket);
    }

}
