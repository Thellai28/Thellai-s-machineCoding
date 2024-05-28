package model;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private static int idGenerator = 1;
    private int id;
    private String description;
    private double amountPaid;
    private String paidBy;
    private List<String> usersInvolved;
    private List<Split> splitList;

    public Expense( double amountPaid, String paidBy, String description ) {
        this.amountPaid = amountPaid;
        this.paidBy = paidBy;
        this.description = description;
        this.id = idGenerator++;
        usersInvolved = new ArrayList<>();
        splitList = new ArrayList<>();
    }

    // -----------------------< Getters & Setters >-----------------------------------

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplitList() {
        return splitList;
    }

    public List<String> getUsersInvolved() {
        return usersInvolved;
    }

    public void setUsersInvolved( List<String> usersInvolved ) {
        this.usersInvolved = usersInvolved;
    }
}
