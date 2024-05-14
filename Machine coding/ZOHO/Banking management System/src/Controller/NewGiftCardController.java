package Controller;

import model.Customer;
import model.GiftCard;
import model.Transaction;
import service.UserInputOutputService;

public class NewGiftCardController {

    public static void addNewGiftCard(){
        GiftCard createdGiftCard = createNewGiftCard();

        if( createdGiftCard != null ){
            Transaction transaction = createTransaction(createdGiftCard);
            addTransactionIntoGiftCardTransactionList(createdGiftCard, transaction);
            addGiftCardIntoCustomerAccount(createdGiftCard);
        }
    }

    private static GiftCard createNewGiftCard(){
        int bankBalanceOfLoggedInCustomer = AuthenticationController.logged_inCustomer.getAccountBalance();
        int balanceInGiftCard = UserInputOutputService
                .displayAccountBalanceAndGetTopUpAmount(bankBalanceOfLoggedInCustomer);

        if( balanceInGiftCard > 0  ){
            reduceBalanceInCustomerAccount(balanceInGiftCard);
            return new GiftCard(balanceInGiftCard);
        }else{
            String message = "❌❌❌ Insufficient Account balance to create gift card";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
        return null;
    }

    private static void reduceBalanceInCustomerAccount(int amountToDetect ){
        Customer loggedInCustomer = AuthenticationController.logged_inCustomer;
        int currentBalance = loggedInCustomer.getAccountBalance();
        AuthenticationController.logged_inCustomer.setAccountBalance( currentBalance - amountToDetect);
    }

    private static void addGiftCardIntoCustomerAccount( GiftCard giftCard ){
        AuthenticationController.logged_inCustomer.getGiftCardList().add(giftCard);
    }

    private static Transaction createTransaction(GiftCard giftCard){
        Customer loggedInCustomer = AuthenticationController.logged_inCustomer;
        return new Transaction(giftCard.getBalance(),
                loggedInCustomer.getAccountNumber(), giftCard.getGiftCardId() );
    }

    private static void addTransactionIntoGiftCardTransactionList( GiftCard giftCard, Transaction transaction ){
        giftCard.getTransactionList().add(transaction);
    }
}
