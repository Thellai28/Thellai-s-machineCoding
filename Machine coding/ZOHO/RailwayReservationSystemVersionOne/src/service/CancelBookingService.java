package service;

import model.Seat;
import model.Ticket;
import model.Passenger;
import repository.RailwayReservationSystemRepository;

import java.util.List;

public class CancelBookingService {

    public static void moveOneUserFromRacToConfirmation( Seat cancelledSeat ){
        Passenger passengerFromRacQueue = RailwayReservationSystemRepository.getUserFromRacQueue();

        if( passengerFromRacQueue != null ){

            Seat racSeat = getSeatOfRacUser(passengerFromRacQueue);
            if( racSeat != null ){
                removeUserFromSeat(racSeat, passengerFromRacQueue);

                addUserIntoSeat(cancelledSeat, passengerFromRacQueue);
                changeBerthPreferenceOfUser(cancelledSeat, passengerFromRacQueue);

                String message = "‼️‼️ moving " + passengerFromRacQueue.getName() +" from Rac to confirmed ticket";
                UserInputOutputService.printMessageAndAddOneBlankLine(message);

                Ticket ticket = createTicket(passengerFromRacQueue, cancelledSeat);
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
        Passenger passengerFromWaitingList = RailwayReservationSystemRepository.getUserFromWaitingList();

        if( passengerFromWaitingList != null ){

            Seat availableRacSeat = getAvailableRacSeat();
            if( availableRacSeat != null ){
                String message = "‼️‼️Moving " + passengerFromWaitingList.getName()+ " from Waiting list to RAC list";
                UserInputOutputService.printMessageAndAddOneBlankLine(message);

                addUserIntoSeat(availableRacSeat, passengerFromWaitingList);
                RailwayReservationSystemRepository.addIntoRacQueue(passengerFromWaitingList);

                Ticket ticket = createTicket(passengerFromWaitingList, availableRacSeat);
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

    public static void removeUserFromSeat( Seat seat, Passenger passenger ){
        seat.getUserList().remove(passenger);
    }



    private static Ticket createTicket( Passenger passenger, Seat chosenSeatForBooking ){
        return new Ticket(passenger, chosenSeatForBooking);
    }

    private static void saveTicketIntoTicketMap( Ticket ticket ){
        RailwayReservationSystemRepository.addTicketIntoTicketMap(ticket);
    }

    private static void addUserIntoSeat( Seat seat, Passenger passenger ){
        seat.getUserList().add(passenger);
    }

    private static Seat getSeatOfRacUser( Passenger passenger ){
        List<Seat> allSeatsList = RailwayReservationSystemRepository.getAllSeats();

        for( Seat seat : allSeatsList ){
            if( seat.getBerth().equalsIgnoreCase("RAC") && seat.getUserList().contains(passenger) ){
                return seat;
            }
        }
        return null;
    }

    private static Seat getAvailableRacSeat(){
        return RailwayReservationSystemRepository.getPreferredSeat("RAC");
    }

    private static void changeBerthPreferenceOfUser(Seat cancelledSeat, Passenger passenger ){
        passenger.setBerthPreference(cancelledSeat.getBerth());
    }
}
