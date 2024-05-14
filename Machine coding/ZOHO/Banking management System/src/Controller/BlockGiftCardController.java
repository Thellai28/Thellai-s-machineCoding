package Controller;

import model.BankingSystem;
import model.Customer;
import model.GiftCard;
import repository.BankingSystemRepository;
import service.UserInputOutputService;

import java.util.List;

public class BlockGiftCardController {
    public static void handleBlockingRequest(){
        List<GiftCard> allGiftCards = AuthenticationController.logged_inCustomer.getGiftCardList();

        if( allGiftCards.size() >  0 ){
            int idx = UserInputOutputService.displayGiftCardsAndGetResponse(allGiftCards);
            GiftCard selectedGiftCard = allGiftCards.get(idx);
            proceedWithBlocking(selectedGiftCard);

        }else{
            String message = "❌❌❌ Sorry there is no Gift card to cancel";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }

    }

    private static void moveBalanceInGiftCardToMainAccount( GiftCard selectedGiftCard ){
        int giftCardBalance = selectedGiftCard.getBalance();
        selectedGiftCard.setBalance(0);
        addMoneyIntoMainAccount(giftCardBalance);
    }

    private static void addMoneyIntoMainAccount( int moneyToBeAdded ){
        Customer loggedInCustomer = AuthenticationController.logged_inCustomer;
        int currBalance = loggedInCustomer.getAccountBalance();
        loggedInCustomer.setAccountBalance( currBalance + moneyToBeAdded );
    }

    private static void moveGiftCardToBlockedList( GiftCard selectedGiftCard ){
       List<GiftCard> giftCardList = AuthenticationController.logged_inCustomer.getGiftCardList();
       giftCardList.remove(selectedGiftCard);
       List<GiftCard> blockedGiftCardList = AuthenticationController.logged_inCustomer.getBlockedGiftCardList();
        blockedGiftCardList.add(selectedGiftCard);
    }

    private static void proceedWithBlocking(GiftCard selectedGiftCard){
        int giftCardBalance = selectedGiftCard.getBalance();
        moveBalanceInGiftCardToMainAccount(selectedGiftCard);
        moveGiftCardToBlockedList(selectedGiftCard);

        int mainAccountBalance = AuthenticationController.logged_inCustomer.getAccountBalance();
        UserInputOutputService.displayGiftCardBlockedConfirmationMessage(giftCardBalance, mainAccountBalance);

    }


}
