package repository;

import model.BankingSystem;
import model.Customer;

public class BankingSystemRepository {

    private static BankingSystem bankingSystem;

    public static void initializeBankingSystemRepository(){
        bankingSystem = new BankingSystem();
    }

    public static String getEncryptedPassword( String username ){
        return bankingSystem.getUsernamePasswordMap().get(username);
    }

    public static Customer getCustomerFromCustomerMap(String username){
        return bankingSystem.getCustomerMap().get(username);
    }
}
