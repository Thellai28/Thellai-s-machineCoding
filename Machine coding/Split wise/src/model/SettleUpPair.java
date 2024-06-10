package model;

public class SettleUpPair {
    private String description;
    private Payable payable;

    public SettleUpPair( String description, Payable payable ) {
        this.description = description;
        this.payable = payable;
    }

    // ------------------< Getters >-----------------------------------
    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Payable getPayable() {
        return payable;
    }

    public void setPayable( Payable payable ) {
        this.payable = payable;
    }
}
