package com.pluralsight;
import com.pluralsight.models.Products;
import com.pluralsight.ui.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    private static final String fileName = "data/Products.csv";
    private static final ArrayList<Products> inventory = loadInventory();

    public static void main(String[] args){
        int option;

        do {
            displayMenu();
            option = Console.promptForInt("Enter an option: ");

            switch(option){
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    //search by product name
                    break;
                case 3:
                    //Search by price
                    break;
                case 4:
                    //Search by department
                    break;
                default:
                    Console.errorMessage();
                    break;
            }
        }
        while (option != 4);

    }
    private static void productHeader(int pageNum){
        System.out.printf("%50s %d %n", "Page", pageNum);
        System.out.printf("%5s %-10s %-55s %-15s %s %n", "SKU","", "Name of Product", "Price", "Department");
        System.out.println("-".repeat(100));
    }

    private static void incrementProductPage(int counter,int currentPageNum){
        int numOfItemsLeft = inventory.size() - counter;
        if (numOfItemsLeft <= 0){
            System.out.println("You have reached the Last page :( Enter a Different option.");
        }
        else if (currentPageNum < 1){
            System.out.println("You are currently on Page 1. Choose another option.");
        }
        else if (numOfItemsLeft >= 10) {
            productHeader(currentPageNum);
            for (int i = ((currentPageNum * 10) - 10); i < (currentPageNum * 10); i++ ){
                System.out.println(inventory.get(i));
                numOfItemsLeft-= 1;
            }
        }
        else{
            System.out.printf("%50s %d %n", "Page", currentPageNum);
            productHeader(currentPageNum);
            int lastItemIndex = numOfItemsLeft;
            for (int i = ((currentPageNum * 10) - 10); i < ((currentPageNum * 10) - lastItemIndex); i++ ){
                System.out.println(inventory.get(i));
                numOfItemsLeft-= 1;
            }
        }
    }
    private static void displayAllProducts(){
        String option;
        int counter = 0;
        int pageNum = 1;
        incrementProductPage(counter, pageNum);
        do {
            option = Console.promptForString("[B] Previous Page [N] Next Page [A] Add item to Cart [X] Main Menu \n> ");
            switch(option.toUpperCase()){
                case "B":
                    counter-= 10;
                    pageNum--;
                    incrementProductPage(counter, pageNum);
                    break;
                case "N":
                    counter+= 10;
                    pageNum++;
                    incrementProductPage(counter, pageNum);
                    break;
                case "A":
                    //Add item to cart
                    break;
                case "X":
                    //back to main menu
                    break;
                default:
                    Console.errorMessage();
            }
        }
        while (!option.equalsIgnoreCase("x"));


    }
    private static void displayMenu(){
        System.out.println("""
                ---------Hardware Store Main Menu---------
                \t1 - View All Products
                \t2 - Search by Product name
                \t3 - Search by Price'
                \t4 - Search by Department
                \t5 - Exit Application""");
    }
    private static ArrayList<Products> loadInventory(){
        ArrayList<Products> inventory = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
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
}
