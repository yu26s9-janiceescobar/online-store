package com.pluralsight.models;

public class Products {
    private String sku;
    private String name;
    private double price;
    private String department;

    /**
     * Creates a product with SKU, name, price, and department.
     * @param sku
     * @param name
     * @param price
     * @param department
     */
    public Products(String sku, String name, double price, String department){
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public String getSku(){
        return sku;
    }

    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDepartment(){
        return department;
    }

    /**
     * Formats product information into a formatted string.
     * @return the String containing product information.
     */
    @Override
    public String toString(){
        return String.format("%-15s %-55s $%,-15.2f %s", sku, name, price, department);
    }

}
