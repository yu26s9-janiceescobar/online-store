package com.pluralsight.models;

import com.pluralsight.ui.Console;

import java.io.*;
import java.util.ArrayList;

public class DataManager {
    private static final String productsInfo = "data/Products.csv";
    private static final String customerInfo = "data/Customers.csv";

    /**
     * Loads inventory of products from a file containing preloaded inventory data.
     * @return the ArrayList of products currently in the inventory.
     */
    public static ArrayList<Products> loadInventory(){
        ArrayList<Products> inventory = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(productsInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            bufReader.readLine();
            String line;
            while ((line = bufReader.readLine()) != null) {
                String[] product = line.split("\\|");
                String sku = product[0];
                String name = product[1];
                double price = Double.parseDouble(product[2]);
                String department = product[3];

                Products item = new Products(sku, name, price, department);

                inventory.add(item);
            }
            bufReader.close();
        }
        catch (IOException e){
            System.out.print("Error: " + e.getMessage());
        }
        return inventory;
    }

    /**
     * Loads customer information from a file containing preloaded customers.
     * @return the ArrayList of customers in the file.
     */
    public static ArrayList<Customers> loadCustomers(){
        ArrayList<Customers> customer = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(customerInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;
            bufReader.readLine();
            while ((line = bufReader.readLine()) != null){
                String[] customerInformation = line.split("\\|");
                String name = customerInformation[0];
                String email = customerInformation[1];
                int age = Integer.parseInt(customerInformation[2]);
                Customers c = new Customers(name, email, age);
                customer.add(c);
            }
            bufReader.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return customer;
    }

    /**
     * Adds a new customer to a customers file.
     * @param name the name of the customer.
     * @param email the email of the customer.
     * @param age the age of the customer.
     */
    public static void addCustomer(String name, String email, int age){
        try {
            FileWriter fileWriter = new FileWriter(customerInfo, true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            bufWriter.write(String.format("%s|%s|%d", name, email, age));
            bufWriter.close();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
