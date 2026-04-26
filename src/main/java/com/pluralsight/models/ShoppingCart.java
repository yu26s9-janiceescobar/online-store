package com.pluralsight.models;
import java.util.ArrayList;

public class ShoppingCart {
    private Customers customer;
    private final ArrayList<Products> items;
    private int totalPennies;

    /**
     * Creates a new shopping cart for customer.
     * @param customer the customer who owns the shopping cart.
     */
    public ShoppingCart(Customers customer){
        this.customer = customer;
        this.items = new ArrayList<>();
        this.totalPennies = 0;
    }

    public Customers getCustomer(){
        return customer;
    }
    public void setCustomer(Customers customer){
        this.customer = customer;
    }
    public ArrayList<Products> getItems(){
        return items;
    }
    public void setItems(Products items){
        this.items.add(items);
    }
    public int getTotalPennies() {
        calculateTotaLPennies();
        return totalPennies;
    }

    /**
     * Calculates total penny amount of all the items in the customer's shopping cart.
     */
    public void calculateTotaLPennies(){
        totalPennies = 0;
        for (Products p : items) {
            totalPennies += (int) (p.getPrice() * 100);
        }
    }

    /**
     * String of number of items in the customer's shopping cart and the total amount of items.
     * @return the String with customer's shopping cart information.
     */
    @Override
    public String toString(){
        calculateTotaLPennies();
        return String.format("""
                Number of items: %d
                Total Price: $%.2f
                """, items.size(), totalPennies / 100.0);
    }
}
