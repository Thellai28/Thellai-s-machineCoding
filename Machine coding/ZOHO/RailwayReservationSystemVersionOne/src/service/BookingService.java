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
        int idx = UserInputOutputService.displayListContentAndGetResponse(availableBerths);
        String berthPreference = availableBerths.get(idx);

        return new User(age, berthPreference, gender, name);
    }


    public static void bookLowerBerth( User user ){

        if( isEligibleForLowerSeat(user) ){
            Seat chosenSeat = RailwayReservationSystemRepository.getPreferredSeat(user.getBerthPreference());
            changeSeatStatus(user, chosenSeat);
            Ticket ticket = createTicket(user, chosenSeat);
            saveTicketIntoTicketMap(ticket);

            UserInputOutputService.printTicketConfirmationMessage(ticket);
        }else{
            System.out.println("☹️☹️ Sorry Your are not eligible to select LOWER berth, choose another berth");
        }
    }


    public static void bookUpperOrMiddleBerth( User user ){
        Seat chosenSeat = RailwayReservationSystemRepository.getPreferredSeat(user.getBerthPreference());
        changeSeatStatus(user, chosenSeat);
        Ticket ticket = createTicket(user, chosenSeat);
        saveTicketIntoTicketMap(ticket);

        UserInputOutputService.printTicketConfirmationMessage(ticket);
    }


    public static void bookRacBerth( User user ){
        boolean isRacFull =  RailwayReservationSystemRepository.isRacFull();
        if( !isRacFull ){
            Seat chosenSeat = RailwayReservationSystemRepository.getPreferredSeat(user.getBerthPreference());
            changeSeatStatus(user, chosenSeat);
            addUserIntoRacQueue(user);

            Ticket ticket = createTicket(user, chosenSeat);
            saveTicketIntoTicketMap(ticket);

            UserInputOutputService.printTicketConfirmationMessage(ticket);
        }else{
            boolean isWaitingListFull = RailwayReservationSystemRepository.isWaitingListFull();

            if( !isWaitingListFull ){
                addUserIntoWaitingList(user);
                System.out.println("Your preferred seat is not available, so adding you into waiting list");
            }else{
                System.out.println("☹️☹️ sorry, No seat available for booking ");
            }
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

    private static void changeSeatStatus( User user, Seat chosenSeat ){
        chosenSeat.getUserList().add(user);
    }
    private static void addUserIntoRacQueue( User user ){
        RailwayReservationSystemRepository.addIntoRacQueue(user);
    }

    private static void addUserIntoWaitingList( User user ){
        RailwayReservationSystemRepository.addIntoWaitingQueue(user);
    }

    private static Ticket createTicket( User user, Seat chosenSeatForBooking ){
        return new Ticket(user, chosenSeatForBooking);
    }

    private static void saveTicketIntoTicketMap( Ticket ticket ){
        RailwayReservationSystemRepository.addTicketIntoTicketMap(ticket);
    }

}
