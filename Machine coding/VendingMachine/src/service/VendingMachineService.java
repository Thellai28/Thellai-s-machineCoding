package service;

import model.Product;
import model.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineService {
    private static VendingMachine vendingMachine;

    public  static void initializeVendingMachineService(){
        vendingMachine = new VendingMachine();
    }

    public static List<Product> fetchAllAvailableProducts(){
        List<Product>products = new ArrayList<>();
        for( Product currProduct : vendingMachine.getProductList()){
            if( currProduct.getQuantity() > 0 ){
                products.add(currProduct);
            }
        }
        return products;
    }

    public static List<Product> getAllProductsFromVendingMachine(){
        return vendingMachine.getProductList();
    }

    public static void addProductIntoProductList( Product product ){
        vendingMachine.getProductList().add(product);
    }
}
