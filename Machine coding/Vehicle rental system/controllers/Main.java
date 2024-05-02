package controllers;


import services.RentalSystemService;
import services.TimerService;
import services.UserIoService;


public class Main {
    public static boolean isRentalServiceActive = true;

    public static void main( String[] args ) {

        initializeAllServices();
        while(isRentalServiceActive){
            int request = UserIoService.displayAndGetUserChoice();
            RequestHandler.handleRequest(request);
        }
    }

    public static void shutDownRentalService(){
        isRentalServiceActive = false;
        TimerService.stopTimer();
    }

    private static void initializeAllServices(){
        TimerService.startTimer();// Timer service should be initialized first :
        RentalSystemService.initializeRentalSystemService();
    }
}
