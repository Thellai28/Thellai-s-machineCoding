package model;

import java.util.ArrayList;
import java.util.List;

public class GiftCard {
    private static int giftCardIdGenerator = 11223;
    private static int giftCardPinGenerator = 1111;
    private int giftCardId;
    private int giftCardPin;
    private int balance;
    private int rewardPoints;
    private List<Transaction> transactionList;

    public GiftCard(int balance) {
        this.giftCardPin = giftCardPinGenerator++;
        this.giftCardId = giftCardIdGenerator++;
        this.balance = balance;
        this.transactionList = new ArrayList<>();
        this.rewardPoints = 0;
    }

    //---------------------------< Getter & Setter >-----------------------------------------------
    public int getBalance() {
        return balance;
    }

    public void setBalance( int balance ) {
        this.balance = balance;
    }

    public int getGiftCardId() {
        return giftCardId;
    }

    public void setGiftCardId( int giftCardId ) {
        this.giftCardId = giftCardId;
    }

    public static int getGiftCardIdGenerator() {
        return giftCardIdGenerator;
    }

    public static void setGiftCardIdGenerator( int giftCardIdGenerator ) {
        GiftCard.giftCardIdGenerator = giftCardIdGenerator;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList( List<Transaction> transactionList ) {
        this.transactionList = transactionList;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints( int rewardPoints ) {
        this.rewardPoints = rewardPoints;
    }

    public int getGiftCardPin() {
        return giftCardPin;
    }

    public void setGiftCardPin( int giftCardPin ) {
        this.giftCardPin = giftCardPin;
    }
}
