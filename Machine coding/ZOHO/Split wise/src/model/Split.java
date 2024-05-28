package model;

public class Split {
    private static int idGenerator = 1;
    private int id;
    private String paidBy;
    private String Recipient;
    private double totalAmount;
    private SplitMethods splitMethods;
    private float splitPercentage;
    private double amountToPay;
    private PaymentStatus paymentStatus;

    public Split( double amountToPay, String paidBy,
                  String recipient, SplitMethods splitMethods,
                  double totalAmount ) {
        this.amountToPay = amountToPay;
        this.paidBy = paidBy;
        Recipient = recipient;
        this.splitMethods = splitMethods;
        this.totalAmount = totalAmount;
        this.id = idGenerator++;
        this.splitPercentage = 0f;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public Split() {
        this.id = idGenerator++;
    }

    //----------------< Getters & Setters >----------------------------------

    public double getAmountToPay() {
        return amountToPay;
    }

    public int getId() {
        return id;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public String getRecipient() {
        return Recipient;
    }

    public SplitMethods getSplitMethods() {
        return splitMethods;
    }

    public float getSplitPercentage() {
        return splitPercentage;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setAmountToPay( double amountToPay ) {
        this.amountToPay = amountToPay;
    }

    public void setPaidBy( String paidBy ) {
        this.paidBy = paidBy;
    }

    public void setRecipient( String recipient ) {
        Recipient = recipient;
    }

    public void setSplitMethods( SplitMethods splitMethods ) {
        this.splitMethods = splitMethods;
    }

    public void setSplitPercentage( float splitPercentage ) {
        this.splitPercentage = splitPercentage;
    }

    public void setTotalAmount( double totalAmount ) {
        this.totalAmount = totalAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus( PaymentStatus paymentStatus ) {
        this.paymentStatus = paymentStatus;
    }
}
