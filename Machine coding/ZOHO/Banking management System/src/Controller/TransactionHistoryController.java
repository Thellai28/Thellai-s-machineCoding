package Controller;

import model.GiftCard;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryController {

    public static void handleDisplayTransactionHistoryRequest(){
        List<GiftCard> customerGiftCards = getAllGiftCardsFromCustomer();
        List<GiftCard>giftCardsWIthTransactionHistory = filterGitCardsWithTransactionHistory(customerGiftCards);

        if( giftCardsWIthTransactionHistory.size() > 0 ){
            UserInputOutputService.displayTransactionHistory(giftCardsWIthTransactionHistory);

        }else{
            String message = "❌❌❌ There is no Transactions History to display";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }

    private static List<GiftCard> getAllGiftCardsFromCustomer(){
        return AuthenticationController.logged_inCustomer.getGiftCardList();
    }

    private static List<GiftCard> filterGitCardsWithTransactionHistory( List<GiftCard> customerGiftCards ){
        List<GiftCard> giftCardsWIthTransactionHistory = new ArrayList<>();

        for( GiftCard currGiftCard : customerGiftCards ){
            if( currGiftCard.getTransactionList().size() > 0 ){
                giftCardsWIthTransactionHistory.add(currGiftCard);
            }
        }
        return giftCardsWIthTransactionHistory;
    }
}
