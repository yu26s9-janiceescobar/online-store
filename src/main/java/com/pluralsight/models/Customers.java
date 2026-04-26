package com.pluralsight.models;

public class Customers {
    private String name;
    private String email;
    private int age;
    private ShoppingCart cart;

    /**
     * Creates a Customer containing name, email, age of customer and assigns a new shopping cart to new customer.
     * @param name the name of customer.
     * @param email the email of customer.
     * @param age the age of customer.
     */
    public Customers(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
        this.cart = new ShoppingCart(this);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public ShoppingCart getCart(){
        return cart;
    }
    public void setCart(ShoppingCart cart){
        this.cart = cart;
    }

    /**
     * Formats name, email, and age of customer into a formatted string.
     * @return the String with customer name, email, and age.
     */
    @Override
    public String toString(){
        return String.format("""
                Name: %s
                Email: %s
                Age: %d
                """, name, email, age);
    }
}
