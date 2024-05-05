package model;

public class Product {
    private String name;
    private int quantity;
    private float price;

    public Product( String name, float price, int quantity ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice( float price ) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }
}
