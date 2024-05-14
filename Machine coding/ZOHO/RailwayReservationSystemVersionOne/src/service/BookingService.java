package service;

import model.Seat;
import model.Ticket;
import model.User;
import repository.RailwayReservationSystemRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingService {

    public static User getUserDetails(){

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

        return new User(age, berthPreference, gender, name);
    }


    public static void bookLowerBerth( User user ){

        if( isEligibleForLowerSeat(user) ){
            Seat chosenSeat = RailwayReservationSystemRepository.getPreferredSeat(user.getBerthPreference());
            assignUserForSeat(user, chosenSeat);
            Ticket ticket = createTicket(user, chosenSeat);
            saveTicketIntoTicketMap(ticket);

            UserInputOutputService.printTicketConfirmationMessage(ticket);
        }else{
            String message = "☹️☹️ Sorry Your are not eligible to select LOWER berth, choose another berth";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }


    public static void bookUpperOrMiddleBerth( User user ){
        Seat chosenSeat = RailwayReservationSystemRepository.getPreferredSeat(user.getBerthPreference());
        assignUserForSeat(user, chosenSeat);
        Ticket ticket = createTicket(user, chosenSeat);
        saveTicketIntoTicketMap(ticket);

        UserInputOutputService.printTicketConfirmationMessage(ticket);
    }


    public static void bookRacBerth( User user ){

        boolean isRacFull =  RailwayReservationSystemRepository.isRacFull();
        if( !isRacFull ){
            Seat racSeat = RailwayReservationSystemRepository.getPreferredSeat(user.getBerthPreference());
            if( racSeat != null ){
                assignUserForSeat(user, racSeat);
                addUserIntoRacQueue(user);

                Ticket ticket = createTicket(user, racSeat);
                saveTicketIntoTicketMap(ticket);

                UserInputOutputService.printTicketConfirmationMessage(ticket);
            }else{
                checkAndAddUserIntoWaitingList(user);
            }
        }else{
            checkAndAddUserIntoWaitingList(user);
        }
    }

    private static void checkAndAddUserIntoWaitingList( User user ){
        boolean isWaitingListFull = RailwayReservationSystemRepository.isWaitingListFull();

        if( !isWaitingListFull ){
            addUserIntoWaitingList(user);

            String message = "‼️‼️Your preferred seat is not available, so adding " + user.getName() + " into waiting list";
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


    private static boolean isEligibleForLowerSeat( User user){

        if( user.getAge() >= 60 ){
            return true;
        }else if ( user.getGender().equalsIgnoreCase("FEMALE") ){
            boolean haveChildren = UserInputOutputService.getChildrenInformation();
            if( haveChildren ){
                return true;
            }
        }
        return false;
    }

    private static void assignUserForSeat( User user, Seat chosenSeat ){
        chosenSeat.getUserList().add(user);
    }
    private static void addUserIntoRacQueue( User user ){
        RailwayReservationSystemRepository.addIntoRacQueue(user);
    }

    private static void addUserIntoWaitingList( User user ){
        RailwayReservationSystemRepository.addIntoWaitingListQueue(user);
    }

    private static Ticket createTicket( User user, Seat chosenSeatForBooking ){
        return new Ticket(user, chosenSeatForBooking);
    }

    private static void saveTicketIntoTicketMap( Ticket ticket ){
        RailwayReservationSystemRepository.addTicketIntoTicketMap(ticket);
    }

}
