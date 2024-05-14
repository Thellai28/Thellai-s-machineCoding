package Controller;

import model.Customer;
import model.GiftCard;
import service.PurchaseService;
import service.UserInputOutputService;

import java.util.List;

public class PurchaseController {

    public static void handlePurchaseRequest(){
        List<GiftCard> allGiftCardsList = AuthenticationController.logged_inCustomer.getGiftCardList();

        if( allGiftCardsList.size() > 0 ){
            int idx = UserInputOutputService.displayGiftCardsAndGetResponse(allGiftCardsList);
            GiftCard selectedGiftCard = allGiftCardsList.get(idx);
            PurchaseService.makePurchase(selectedGiftCard);
        }else{
            String message = "❌❌❌ There is no gift card to purchase";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }
    
}
