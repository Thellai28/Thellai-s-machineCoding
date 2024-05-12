package controller;

import model.Seat;
import model.Ticket;
import model.User;
import repository.RailwayReservationSystemRepository;
import service.UserInputOutputService;

public class CancelBookingController {

    public static void handleBookingCancel(){
        int ticketNumber = UserInputOutputService.getTicketNumber();
        Ticket ticket = retrieveTicketFromRepository(ticketNumber);
        changeSeatStatus(ticket.getBookedSeat(), ticket.getBookedUser());

        moveOneUserFromRacToConfirmation(ticket.getBookedSeat());

    }

    private static Ticket retrieveTicketFromRepository( int ticketNumber ){
        return RailwayReservationSystemRepository.getTicket(ticketNumber);
    }

    private static void changeSeatStatus( Seat seat, User user ){
        seat.getUserList().remove(user);
    }

    private static void moveOneUserFromRacToConfirmation( Seat seat ){
        User userFromRacQueue = RailwayReservationSystemRepository.getUserFromRacQueue();

        if( userFromRacQueue != null ){
            System.out.println("‼️‼️ moving " + userFromRacQueue.getName() +" from Rac to confirmed ticket");
            changeSeatStatus(seat, userFromRacQueue);
            Ticket ticket = createTicket(userFromRacQueue, seat);
            saveTicketIntoTicketMap(ticket);

            UserInputOutputService.printTicketConfirmationMessage(ticket);

            moveOneUserFromWaitingListToRac();
        }
    }

    private static void moveOneUserFromWaitingListToRac(){
        User userFromWaitingList = RailwayReservationSystemRepository.getUserFromWaitingList();
        if( userFromWaitingList != null ){
            System.out.println("Moving" + userFromWaitingList.getName()+ " from Waiting list to RAC list");
            RailwayReservationSystemRepository.addIntoRacQueue(userFromWaitingList);
        }
    }

    private static Ticket createTicket( User user, Seat chosenSeatForBooking ){
        return new Ticket(user, chosenSeatForBooking);
    }

    private static void saveTicketIntoTicketMap( Ticket ticket ){
        RailwayReservationSystemRepository.addTicketIntoTicketMap(ticket);
    }
}
