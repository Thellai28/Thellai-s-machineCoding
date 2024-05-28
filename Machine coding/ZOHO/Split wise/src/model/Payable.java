package model;

public class Payable {
    private static int idGenerator = 1;
    private int id;
    private String from;
    private String to;
    private double amount;
    private String groupName;
    private PaymentStatus paymentStatus;

    public Payable( double amount, String from, String groupName, String to ) {
        this.amount = amount;
        this.from = from;
        this.groupName = groupName;
        this.to = to;
        this.id = idGenerator++;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    //----------------< Getter & Setter >------------------------------

    public void setAmount( double amount ) {
        this.amount = amount;
    }

    public void setFrom( String from ) {
        this.from = from;
    }

    public void setTo( String to ) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public double getAmount() {
        return amount;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getId() {
        return id;
    }

    public String getTo() {
        return to;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus( PaymentStatus paymentStatus ) {
        this.paymentStatus = paymentStatus;
    }
}
