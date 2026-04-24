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
    private static boolean pageExists(int currentPageNum){
        int lastPage;
        if (inventory.size() % 10 == 0){
            lastPage = inventory.size() / 10;
        }else{
            lastPage = (inventory.size() / 10) + 1;
        }
        if (lastPage < currentPageNum){
            System.out.println("You have reached the last page :( Please enter another option.");
            return false;
        }else if (currentPageNum < 1){
            System.out.println("You are currently on Page 1. Choose another option.");
            return false;
        }
        return true;
    }

    private static void incrementProductPage(int currentPageNum){
        int previousItemsDisplayed = (currentPageNum * 10) - 10;
        int numOfItemsLeft = inventory.size() - previousItemsDisplayed;
        productHeader(currentPageNum);
        if (numOfItemsLeft >= 10) {
            for (int i = ((currentPageNum * 10) - 10); i < (currentPageNum * 10); i++ ){
                System.out.println(inventory.get(i));
            }
        }
        else{
            for (int i = previousItemsDisplayed; i < previousItemsDisplayed + numOfItemsLeft; i++) {
                System.out.println(inventory.get(i));
            }
        }

    }
    
    private static void displayAllProducts(){
        String option;
        int pageNum = 1;
        incrementProductPage(pageNum);
        do {
            option = Console.promptForString("[P] Previous Page [N] Next Page [A] Add item to Cart [X] Main Menu \n> ");
            switch(option.toUpperCase()){
                case "P":
                    if (pageExists(pageNum - 1)) {
                        pageNum--;
                        incrementProductPage(pageNum);
                    }
                    break;
                case "N":
                    if (pageExists(pageNum + 1)) {
                        pageNum++;
                        incrementProductPage(pageNum);
                    }
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
