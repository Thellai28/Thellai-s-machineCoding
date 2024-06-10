package model;

public class Split {
    private static int idGenerator = 1;
    private int id;
    private String paidBy;
    private String recipient;
    private double totalAmount;
    private SplitMethod splitMethod;
    private float splitPercentage;
    private double amountToPay;
    private PaymentStatus paymentStatus;

    public Split( double amountToPay, String paidBy,
                  String recipient, SplitMethod splitMethod,
                  double totalAmount ) {
        this.amountToPay = amountToPay;
        this.paidBy = paidBy;
        this.recipient = recipient;
        this.splitMethod = splitMethod;
        this.totalAmount = totalAmount;
        this.id = idGenerator++;
        this.splitPercentage = 0f;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public Split() {
        this.id = idGenerator++;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-12s | %-12.2f | %-17s | %-15.2f |", paidBy, recipient,
                totalAmount, splitMethod, amountToPay);
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
        return recipient;
    }

    public SplitMethod getSplitMethods() {
        return splitMethod;
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
        this.recipient = recipient;
    }

    public void setSplitMethods( SplitMethod splitMethod ) {
        this.splitMethod = splitMethod;
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
