package service;

import model.GiftCard;
import model.Transaction;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner( System.in);

    public static int displayHomePageAndGetResponse(){
        System.out.println("Welcome to banking application");
        System.out.println("1. Create gift card ");
        System.out.println("2. TopUp gift card ");
        System.out.println("3. Display GiftCard transaction history");
        System.out.println("4. Block a gift card ");
        System.out.println("5. Purchase from Gift card");
        System.out.println("6. Log-out");
        System.out.println("7. Shut down Banking application");

        return scanner.nextInt();
    }


    public static void displayGiftCardBlockedConfirmationMessage( int giftCardBalance, int mainAccountBalance ){
        System.out.println("🎉🎉🎉 Successfully blocked gift card");
        System.out.println("Moved " + giftCardBalance + " Rs from gift card to your main account");
        System.out.println("Your current main account balance is "+ mainAccountBalance);
        System.out.println();
    }

    public static int displayGiftCardsAndGetResponse( List<GiftCard> giftCardList ){
        System.out.println("Choose a gift card to top up from below list");

        for( int i = 0; i < giftCardList.size(); i++ ){
            GiftCard giftCard = giftCardList.get(i);
            System.out.println(i+1 + ". Gift card id : " + giftCard.getGiftCardId() );
        }
        return scanner.nextInt() -1;
    }

    public static int getPurchaseAmount( int giftCardBalance ){
        System.out.println("you have a balance of "+ giftCardBalance +", Please select the amount of your purchase");
        return scanner.nextInt();
    }


    public static void displayTransactionHistory( List<GiftCard> giftCardList ){
        System.out.println( "📺 Printing Transaction history 📺" );

        for( GiftCard giftCard : giftCardList ){
            System.out.println("Gift Card id : " + giftCard.getGiftCardId());

            int idx = 1;
            for( Transaction currTransaction : giftCard.getTransactionList()){
                System.out.println(idx +". Transferred an amount of "
                        + currTransaction.getAmountTransferred() + "From : "
                        + currTransaction.getFrom() );
                idx++;
            }
            System.out.println("----------------------------------------------------------------------------------------");

        }
    }


    public static void displayNewGiftCardBalance( GiftCard giftCard ){
        System.out.println("🎉🎉🎉 Successfully topped up your gift card");
        System.out.println("Card id : " + giftCard.getGiftCardId());
        System.out.println("New card balance: " + giftCard.getBalance());
        printHorizontalLines();
    }


    public static int displayAccountBalanceAndGetTopUpAmount( int accountBalance ){
        System.out.println("Your account Balance is " + accountBalance + ". Enter the initial GiftCard Balance");

        return scanner.nextInt();
    }


    public static String getUserName(){
        System.out.println("Please enter you name ");
        return scanner.next();
    }


    public static String getPassword(){
        System.out.println("Please enter your password");
        return scanner.next();
    }


    public static void printMessageAndAddOneBlankLine( String message ){
        System.out.println(message);
        System.out.println();
    }


    public static void printHorizontalLines(){
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    public static void closeScanner(){
        scanner.close();
        System.out.println("⚠️⚠️ Closing Scanner...");
    }

    public static int getPinForGiftCard( GiftCard selectedGiftCard ){
        System.out.println("Please enter security pin for giftCard with id : " + selectedGiftCard.getGiftCardId() );
        return scanner.nextInt();
    }

    public static void printAccountBalanceAfterEarningRewardPoints( int amountEarnedFromRewards, int accountBalance ){
        System.out.println("🎉🎉🎉Purchase is successful, amount earned via rewards points is "
                + amountEarnedFromRewards + " Rs");
        System.out.println("💸💸💸Your new account balance is " + accountBalance);
        printHorizontalLines();

    }

    public static void printAuthenticationSuccessfulMessage( String username ){
        System.out.println("🎉🎉🎉 Authentication successful !");
        System.out.println("Welcome back " + username );
        System.out.println();
    }
}
