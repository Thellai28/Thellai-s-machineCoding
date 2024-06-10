package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private static int idGenerator = 1;
    private final int id;
    private final String groupName;
    private final String admin;
    private final List<String> participantsList;
    private final List<Expense> expenseList;
    private final List<Payable> payableList;
    private final List<String> transactionHistory;

    public Group( String admin, String groupName ) {
        this.admin = admin;
        this.groupName = groupName;
        this.id = idGenerator++;
        this.participantsList = new ArrayList<>();
        this.expenseList = new ArrayList<>();
        this.payableList = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
    }

    //---------------------< Getters & Setters >---------------------------

    public String getAdmin() {
        return admin;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getId() {
        return id;
    }

    public List<String> getParticipantsList() {
        return participantsList;
    }

    public List<Payable> getPayableList() {
        return payableList;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
