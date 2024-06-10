package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int idGenerator = 1;
    private int id;
    private String name;
    private List<String> groupsList;
    private List<Payable> payablelist;
    private List<String> transactionHistory;

    public User( String name ) {
        this.name = name;
        this.groupsList = new ArrayList<>();
        this.payablelist = new ArrayList<>();
        this.id = idGenerator++;
        this.transactionHistory = new ArrayList<>();
    }

    //------------------< Getter & Setter >-------------------------------

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getGroupsList() {
        return groupsList;
    }

    public List<Payable> getPayablelist() {
        return payablelist;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
