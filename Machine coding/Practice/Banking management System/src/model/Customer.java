package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int accountNumberGenerator = 1213141516;
    private int accountNumber;
    private String userName;
    private int accountBalance;
    private List<GiftCard> giftCardList;
    private List<GiftCard> blockedGiftCardList;
    private List<Transaction> customerTransactionList;

    public Customer( int accountBalance, String userName ) {
        this.giftCardList = new ArrayList<>();
        this.customerTransactionList = new ArrayList<>();
        this.accountNumber = accountNumberGenerator++;
        this.accountBalance = accountBalance;
        this.userName = userName;
        this.blockedGiftCardList = new ArrayList<>();
    }

    //--------------< Getters & setters >-----------------------------------------------

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance( int accountBalance ) {
        this.accountBalance = accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber( int accountNumber ) {
        this.accountNumber = accountNumber;
    }

    public static int getAccountNumberGenerator() {
        return accountNumberGenerator;
    }

    public static void setAccountNumberGenerator( int accountNumberGenerator ) {
        Customer.accountNumberGenerator = accountNumberGenerator;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public List<Transaction> getCustomerTransactionList() {
        return customerTransactionList;
    }

    public void setCustomerTransactionList( List<Transaction> customerTransactionList ) {
        this.customerTransactionList = customerTransactionList;
    }

    public List<GiftCard> getGiftCardList() {
        return giftCardList;
    }

    public void setGiftCardList( List<GiftCard> giftCardList ) {
        this.giftCardList = giftCardList;
    }

    public List<GiftCard> getBlockedGiftCardList() {
        return blockedGiftCardList;
    }

    public void setBlockedGiftCardList( List<GiftCard> blockedGiftCardList ) {
        this.blockedGiftCardList = blockedGiftCardList;
    }
}
