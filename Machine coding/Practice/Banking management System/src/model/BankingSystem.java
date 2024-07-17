package model;

import Controller.AuthenticationController;

import java.util.HashMap;
import java.util.Map;

public class BankingSystem {
    private Map<String, Customer> customerMap;
    private Map<String, String> usernamePasswordMap;

    public BankingSystem() {
        this.customerMap = new HashMap<>();
        this.usernamePasswordMap = new HashMap<>();
        final String[] customers = {"Thellai", "Sanjay", "Prem"};

        createCustomerAndAddIntoCustomerMap(customers);
        encodePasswordAndAddIntoPasswordMap(customers);
    }

    private void createCustomerAndAddIntoCustomerMap( String[] customers){

        for( String name : customers ){
            Customer customer = new Customer(1000, name );
            customerMap.put(customer.getUserName(), customer);
        }
    }

    private void encodePasswordAndAddIntoPasswordMap( String[]customers ){
        final String[] passwords = {"Password@@Thellai", "Password@#Sanjay", "Password@@Prem"};

        for( int i = 0; i < customers.length; i++ ){
            String userName = customers[i];
            String password = passwords[i];

            String encodedPassword = AuthenticationController.encryptPassword(password);
            usernamePasswordMap.put(userName, encodedPassword);
        }
    }


    //----------------------< Getter & Setter >------------------------------------------

    public Map<String, Customer> getCustomerMap() {
        return customerMap;
    }

    public void setCustomerMap( Map<String, Customer> customerMap ) {
        this.customerMap = customerMap;
    }

    public Map<String, String> getUsernamePasswordMap() {
        return usernamePasswordMap;
    }

    public void setUsernamePasswordMap( Map<String, String> usernamePasswordMap ) {
        this.usernamePasswordMap = usernamePasswordMap;
    }
}
