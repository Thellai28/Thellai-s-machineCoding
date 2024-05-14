package service;

import Controller.AuthenticationController;
import model.Customer;
import model.GiftCard;

public class PurchaseService {

    public static void makePurchase( GiftCard selectedGiftCard ){

        if( selectedGiftCard.getBalance() > 0 ){
            int giftCardPin = UserInputOutputService.getPinForGiftCard(selectedGiftCard);
            proceedIfPinIsCorrect(giftCardPin, selectedGiftCard);

        }else{
            String message = "❌❌❌ Sorry, you don't have sufficient balance make a purchase.";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }

    private static void proceedIfPinIsCorrect( int giftCardPin, GiftCard selectedGiftCard ){

        if( giftCardPin == selectedGiftCard.getGiftCardPin() ){

            int purchaseAmount = UserInputOutputService.getPurchaseAmount(selectedGiftCard.getBalance());
            int rewardPointEarned = calculateRewardPoints(purchaseAmount);

            updateRewardPointInGiftCard(selectedGiftCard, rewardPointEarned);
            int amountFromRewardPoints = calculateAmountEarnedFromRewardPoints(selectedGiftCard);
            addRewardPointAmountInMainAccount(amountFromRewardPoints);

            int updatedBalance = getCustomerAccountBalance();
            UserInputOutputService
                    .printAccountBalanceAfterEarningRewardPoints(amountFromRewardPoints, updatedBalance);

        }else{
            String message = "❌❌❌ Sorry, the pin does not match, please enter a valid pin";
            UserInputOutputService.printMessageAndAddOneBlankLine(message);
        }
    }

    private static int calculateRewardPoints( int purchaseAmount ){
        final int REWARD_POINTS_PER_HUNDRED = 1;
        int countOfHundreds = purchaseAmount / 100;
        return countOfHundreds * REWARD_POINTS_PER_HUNDRED;
    }

    private static void updateRewardPointInGiftCard( GiftCard selectedGiftCard, int rewardPointsEarned ){
        int currRewardPoint = selectedGiftCard.getRewardPoints();
        selectedGiftCard.setRewardPoints( currRewardPoint + rewardPointsEarned);
    }

    private static int calculateAmountEarnedFromRewardPoints( GiftCard selectedGiftCard ){
        final int CASH_PER_TEN_REWARD_POINTS = 10;
        int remainingRewardPoints = selectedGiftCard.getRewardPoints() % 10; // We calculate cash for every 10 points :
        int eligibleRewardPoints = selectedGiftCard.getRewardPoints() / 10;

        selectedGiftCard.setRewardPoints( remainingRewardPoints );
        return eligibleRewardPoints * CASH_PER_TEN_REWARD_POINTS;
    }

    private static void addRewardPointAmountInMainAccount( int amountToAdd ){
        Customer loggedInCustomer =  AuthenticationController.logged_inCustomer;
        int currBalance = loggedInCustomer.getAccountBalance();

        loggedInCustomer.setAccountBalance( currBalance + amountToAdd);
    }

    private static int getCustomerAccountBalance(){
        return  AuthenticationController.logged_inCustomer.getAccountBalance();
    }
}
