package model;

public class Transaction {
    private static int transactionIdBaseValue = 1;

    private int transactionId;
    private int from;
    private int to;
    private int amountTransferred;

    public Transaction( int amountTransferred,  int from, int to ) {
        this.amountTransferred = amountTransferred;
        this.transactionId = transactionIdBaseValue++;
        this.from = from;
        this.to = to;
    }

    //-----------< Getter & setter > --------------------------------------


    public int getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred( int amountTransferred ) {
        this.amountTransferred = amountTransferred;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom( int from ) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo( int to ) {
        this.to = to;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId( int transactionId ) {
        this.transactionId = transactionId;
    }
}
