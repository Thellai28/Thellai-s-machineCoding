package service;

import model.Seat;
import model.Ticket;
import model.User;
import repository.RailwayReservationSystemRepository;

import java.util.List;

public class CancelBookingService {

    public static void moveOneUserFromRacToConfirmation( Seat cancelledSeat ){
        User userFromRacQueue = RailwayReservationSystemRepository.getUserFromRacQueue();

        if( userFromRacQueue != null ){

            Seat racSeat = getSeatOfRacUser(userFromRacQueue);
            if( racSeat != null ){
                removeUserFromSeat(racSeat, userFromRacQueue);

                addUserIntoSeat(cancelledSeat, userFromRacQueue);
                changeBerthPreferenceOfUser(cancelledSeat, userFromRacQueue);

                String message = "‼️‼️ moving " + userFromRacQueue.getName() +" from Rac to confirmed ticket";
                UserInputOutputService.printMessageAndAddOneBlankLine(message);

                Ticket ticket = createTicket(userFromRacQueue, cancelledSeat);
                saveTicketIntoTicketMap(ticket);

                UserInputOutputService.printTicketConfirmationMessage(ticket);

                moveOneUserFromWaitingListToRac();
            }

        }else{
            String message = "⚠️ Sorry, There is no users in RAC berths to confirm ticket ⚠️";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }

    public static void moveOneUserFromWaitingListToRac(){
        User userFromWaitingList = RailwayReservationSystemRepository.getUserFromWaitingList();

        if( userFromWaitingList != null ){

            Seat availableRacSeat = getAvailableRacSeat();
            if( availableRacSeat != null ){
                String message = "‼️‼️Moving " + userFromWaitingList.getName()+ " from Waiting list to RAC list";
                UserInputOutputService.printMessageAndAddOneBlankLine(message);

                addUserIntoSeat(availableRacSeat, userFromWaitingList);
                RailwayReservationSystemRepository.addIntoRacQueue(userFromWaitingList);

                Ticket ticket = createTicket(userFromWaitingList, availableRacSeat);
                UserInputOutputService.printTicketConfirmationMessage(ticket);
            }
        }else{
            String message = "There is no user in waiting list, so no user is moved from waiting list to RAC";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }

    public static Ticket retrieveTicketFromRepository( int ticketNumber ){
        return RailwayReservationSystemRepository.getTicket(ticketNumber);
    }

    public static void removeUserFromSeat( Seat seat, User user ){
        seat.getUserList().remove(user);
    }



    private static Ticket createTicket( User user, Seat chosenSeatForBooking ){
        return new Ticket(user, chosenSeatForBooking);
    }

    private static void saveTicketIntoTicketMap( Ticket ticket ){
        RailwayReservationSystemRepository.addTicketIntoTicketMap(ticket);
    }

    private static void addUserIntoSeat( Seat seat, User user ){
        seat.getUserList().add(user);
    }

    private static Seat getSeatOfRacUser( User user ){
        List<Seat> allSeatsList = RailwayReservationSystemRepository.getAllSeats();

        for( Seat seat : allSeatsList ){
            if( seat.getBerth().equalsIgnoreCase("RAC") && seat.getUserList().contains(user) ){
                return seat;
            }
        }
        return null;
    }

    private static Seat getAvailableRacSeat(){
        return RailwayReservationSystemRepository.getPreferredSeat("RAC");
    }

    private static void changeBerthPreferenceOfUser(Seat cancelledSeat, User user ){
        user.setBerthPreference(cancelledSeat.getBerth());
    }
}
