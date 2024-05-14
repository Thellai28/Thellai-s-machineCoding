package Controller;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch (request){
            case 1 : {
                NewGiftCardController.addNewGiftCard();
                break;
            } case 2 : {
                TopUpController.handleTopUpRequest();
                break;
            } case 3 : {
                TransactionHistoryController.handleDisplayTransactionHistoryRequest();
                break;
            } case 4 : {
                BlockGiftCardController.handleBlockingRequest();
                break;
            } case 5: {
                PurchaseController.handlePurchaseRequest();
                break;
            } case 6 : {
                AuthenticationController.logOut();
                break;
            } case 7: {
                System.out.println("⚠️⚠️ Shutting down banking System...");
                Main.shutDownBankingSystem();
                break;
            } default:{
                System.out.println("❌❌❌ Invalid input !");
                break;
            }
        }
    }
}
