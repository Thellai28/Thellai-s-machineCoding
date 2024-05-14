package Controller;

import model.Customer;
import model.GiftCard;
import service.UserInputOutputService;

import java.util.List;

public class TopUpController {

    public static void handleTopUpRequest(){
        List<GiftCard> giftCardsWithCustomer = getAllGiftCardWithCustomer();

        if( giftCardsWithCustomer.size() > 0 ){
            int idx = UserInputOutputService
                    .displayGiftCardsAndGetResponse(giftCardsWithCustomer);

            GiftCard selectedGiftCard = giftCardsWithCustomer.get(idx);
            proceedWithTopUp(selectedGiftCard);

        }else{
            String message = "❌❌❌ Sorry, you don't have any gift card to top up";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }

    }

    private static List<GiftCard> getAllGiftCardWithCustomer(){
        Customer loggedInCustomer = AuthenticationController.logged_inCustomer;
        return loggedInCustomer.getGiftCardList();
    }

    private static int getBalanceInCustomerAccount(){
        return AuthenticationController.logged_inCustomer.getAccountBalance();
    }

    private static void reduceBalanceInCustomerAccount(int currentBalance , int amountToDetect ){
        AuthenticationController.logged_inCustomer
                .setAccountBalance( currentBalance - amountToDetect);
    }

    private static void topUpSelectedGiftCard( GiftCard selectedGiftCard, int topUpAmount ){
        int currentGiftCardBalance = selectedGiftCard.getBalance();
        selectedGiftCard.setBalance( currentGiftCardBalance + topUpAmount);
    }

    public static void proceedWithTopUp( GiftCard selectedGiftCard ){

        int customerAccountBalance = getBalanceInCustomerAccount();
        int topUpAmount = UserInputOutputService
                .displayAccountBalanceAndGetTopUpAmount(customerAccountBalance);

        reduceBalanceInCustomerAccount(customerAccountBalance, topUpAmount);
        topUpSelectedGiftCard(selectedGiftCard, topUpAmount);

        UserInputOutputService.displayNewGiftCardBalance(selectedGiftCard);
    }

}
