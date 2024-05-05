package model;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private List<Product> productList;

    public VendingMachine(){
        this.productList = new ArrayList<>();
        final String productNames[] = {"Maggie", "Biscuits", "Dry grapes",
                "Roasted Peanuts", "Pepsi", "Coke", "Water bottle"};
        final float price[] = {15.00f, 22.00f, 55.00f, 27.00f, 30.00f, 30.00f, 20.00f };
        final int QUANTITY_PER_PRODUCT = 5;

        fillProductList(productNames, price,QUANTITY_PER_PRODUCT);

    }

    private void fillProductList( String productsNames[], float[] price, int quantityPerProduct ){
        for( int i = 0; i < productsNames.length; i++ ){
            Product product = new Product(productsNames[i], price[i], quantityPerProduct );
            this.productList.add( product );
        }
    }

    // ----------------------------------< GETTERS & SETTERS>-----------------------------------------


    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList( List<Product> productList ) {
        this.productList = productList;
    }
}
