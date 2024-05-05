package controller;

import service.VendingMachineService;

public class RequestHandler {

    public static void handleRequest( int request ){
        switch (request){
            case 1 : {
                VendingMachineController.showAndDispenseProduct();
                break;
            } case 2 : {
                VendingMachineController.receiveReturnedProductAndReturnChange();
                break;
            } case 3 : {
                VendingMachineController.addNewItemIntoVendingMachine();
                break;
            } case 4 :{
                Main.shutDownVendingMachine();
                break;
            } default:{
                System.out.println("‼️❌  invalid input, Please enter a valid input");
                break;
            }
        }
    }
}
