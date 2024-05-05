package service;

import model.Product;

import java.util.List;
import java.util.Scanner;

public class UserIoService {
    private static Scanner scanner = new Scanner( System.in );

    public static void closeScanner(){
        scanner.close();
    }

    public static int showOptionsAndGetRequest(){
        System.out.println("--- Welcome to Vending machine---");
        System.out.println("1. Purchase a product ");
        System.out.println("2. Return an item ");
        System.out.println("3. Add new Product into vending machine");
        System.out.println("4. Exit");
        printHorizontalLines();
        return scanner.nextInt();
    }

    public static int displayProductListAndGetResponse( List<Product> productList ){
        for( int i = 0; i < productList.size(); i++ ){
            Product currProduct = productList.get(i);
            System.out.println(i+1 + ". "+ currProduct.getName() +
                    ", Quantity : " + currProduct.getQuantity() );
        }
        int chosenProduct = scanner.nextInt();
        return chosenProduct -1; // converting 1 based to zero Based;
    }

    public static void displayProducts( List<Product> productList ){
        for( int i = 0; i < productList.size(); i++ ){
            Product currProduct = productList.get(i);
            System.out.println(i+1 + ". "+ currProduct.getName() +
                    ", Quantity : " + currProduct.getQuantity() );
        }
    }

    public static int getQuantityDetails(){
        System.out.println("Please enter the Quantity");
        return scanner.nextInt();
    }

    public static float getPaymentFromUser( float payableAmount ){
        String formattedString = String.format("The total price of chosen product is %.2f💲, Please pay the amount",
                payableAmount);
        System.out.println(formattedString);
        return scanner.nextFloat();
    }

    public static String getProductName(){
        System.out.println("Please enter the product name");
        return scanner.next();
    }

    public static float getProductPrice(){
        System.out.println("Please enter the price of the product");
        return scanner.nextFloat();
    }

    public static void printHorizontalLines(){
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

}
