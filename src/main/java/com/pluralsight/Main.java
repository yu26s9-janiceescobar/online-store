package com.pluralsight;
import com.pluralsight.models.Customers;
import com.pluralsight.models.DataManager;
import com.pluralsight.models.Products;
import com.pluralsight.ui.Console;
import com.pluralsight.models.ShoppingCart;
import java.util.ArrayList;

public class Main {
    private static final ArrayList<Products> inventory = DataManager.loadInventory();
    private static final ArrayList<Customers> customer = DataManager.loadCustomers();

    /**
     * Entry point for program.
     * Displays Menu options and prompts user to choose one.
     * Including display products, view shopping cart, log in, create new account, or exit application.
     * @param args not used in this program.
     */
    public static void main(String[] args) {
        int option;
        Customers account = null;
        do {
            displayMenu();
            option = Console.promptForInt("Enter an option: ", 1, 5);
            switch (option) {
                case 1:
                    account = displayProducts(account);
                    break;
                case 2:
                    if (account != null) {
                        displayCart(account);
                    } else {
                        System.out.println("You need to login first.");
                        account = login();
                        if (account != null){
                            displayCart(account);
                        }
                    }
                    break;
                case 3:
                    if (account != null) {
                        System.out.println("You're already logged in.");
                    } else {
                        account = login();
                    }
                    break;
                case 4:
                    if (account != null) {
                        System.out.println("You're already logged in.");
                    } else {
                        addCustomer();
                        System.out.println("You have successfully created an account. Please try logging in.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting Application...");
                    break;
            }
        }
        while (option != 5);
    }

    /**
     * Displays Products by increments of ten allowing user to move
     * through pages, add items to cart, apply filters to search for products, or exit back to main menu.
     * @param account the customer currently logged in.
     * @return the Customer currently logged in.
     */
    private static Customers displayProducts(Customers account) {
        String option;
        int pageNum = 1;
        setProductPage(pageNum);
        do {
            displayProductsMenu();
            option = Console.promptForOption("> ", "p", "n", "a", "f", "x");
            switch (option.toUpperCase()) {
                case "P":
                    if (pageExists(pageNum - 1)) {
                        pageNum--;
                        setProductPage(pageNum);
                    }
                    break;
                case "N":
                    if (pageExists(pageNum + 1)) {
                        pageNum++;
                        setProductPage(pageNum);
                    }
                    break;
                case "A":
                    if (account != null) {
                        addToCart(account);
                    } else {
                        System.out.println("You need to login first.");
                        account = login();
                        if (account != null) {
                            addToCart(account);
                        }
                    }
                    setProductPage(pageNum);
                    break;
                case "F":
                    searchByProduct();
                    break;
                case "X":
                    break;
            }
        }while (!option.equalsIgnoreCase("x")) ;
        return account;
    }

    private static void displayMenu() {
        System.out.println("""
                ---------Hardware Store Main Menu---------
                \t1 - View All Products
                \t2 - View Shopping Cart
                \t3 - Login
                \t4 - Create an Account
                \t5 - Exit Application""");
    }

    public static Customers login() {
        Customers account;
        String option;
        do {
            String email = Console.promptForString("Enter your email: ");;
            account = checkReturningCustomer(email);
            if (account != null) {
                System.out.println("Welcome Back " + account.getName());
                option = "X";
            } else {
                System.out.print("""
                        Account not Found.
                        [T] Try Again [X] Back to Menu
                        """);
                option = Console.promptForOption("> ","T", "X");
            }
        }
        while (!option.equalsIgnoreCase("X"));
        return account;
    }

    private static void displayProductsMenu() {
        System.out.println("[P] Previous Page [N] Next Page [A] Add item to Cart [F] Add Filters [X] Main Menu");
    }

    private static void productHeader(int pageNum) {
        System.out.printf("%50s %d %n", "Page", pageNum);
        System.out.printf("%5s %-10s %-55s %-15s %s %n", "SKU", "", "Name of Product", "Price", "Department");
        System.out.println("-".repeat(100));
    }

    /**
     * Allows user to search for product using SKU, name, or department of items.
     */
    private static void searchByProduct() {
        String search = Console.promptForString("Enter product you would like to search for: ");
        boolean hasFound = false;
        for (Products item : inventory) {
            if (search.equalsIgnoreCase(item.getSku()) ||
                    item.getName().toLowerCase().contains(search.toLowerCase()) ||
                    item.getDepartment().toLowerCase().contains(search.toLowerCase())) {
                System.out.println(item);
                hasFound = true;

            }
        }
        if (!hasFound) {
            System.out.println("Oops! Sorry No Matches were Found :(");
        }
    }

    private static void displayCart(Customers c) {
        System.out.println(c.getCart());
    }

    private static boolean pageExists(int currentPageNum) {
        int lastPage;
        if (inventory.size() % 10 == 0) {
            lastPage = inventory.size() / 10;
        } else {
            lastPage = (inventory.size() / 10) + 1;
        }
        if (lastPage < currentPageNum) {
            System.out.println("You have reached the last page :( Please enter another option.");
            return false;
        } else if (currentPageNum < 1) {
            System.out.println("You are currently on Page 1. Choose another option.");
            return false;
        }
        return true;
    }

    /**
     * Shows a maximum of ten products at a time allowing to switch from previous ten or next ten.
     * @param currentPageNum the page number the user is currently on.
     */
    private static void setProductPage(int currentPageNum) {
        int previousItemsDisplayed = (currentPageNum * 10) - 10;
        int numOfItemsLeft = inventory.size() - previousItemsDisplayed;
        productHeader(currentPageNum);
        if (numOfItemsLeft >= 10) {
            for (int i = ((currentPageNum * 10) - 10); i < (currentPageNum * 10); i++) {
                System.out.println(inventory.get(i));
            }
        } else {
            for (int i = previousItemsDisplayed; i < previousItemsDisplayed + numOfItemsLeft; i++) {
                System.out.println(inventory.get(i));
            }
        }

    }

    private static void addCustomer() {
        String name;
        int age;
        String email;
        System.out.println("Please fill out the information below.");
        name = Console.promptForString("Enter your name: ");
        email = Console.promptForString("Enter your email: ");
        age = Console.promptForInt("Enter age: ", 0, 110);
        DataManager.addCustomer(name, email, age);
        Customers c = new Customers(name, email, age);
        customer.add(c);
    }

    private static Customers checkReturningCustomer(String email) {
        for (Customers c : customer) {
            if (email.equalsIgnoreCase(c.getEmail())) {
                return c;
            }
        }
        return null;
    }


    private static void addToCart(Customers customer) {
        ShoppingCart cart = customer.getCart();
        String userInput;
        do {
            userInput = Console.promptForString("Enter SKU number of product to add to your cart ([X] to return) \n> ");
            for (Products item : inventory) {
                if (userInput.equalsIgnoreCase(item.getSku())) {
                    cart.setItems(item);
                    System.out.println(item);
                    System.out.println("You have successfully added an item to your cart.");
                    userInput = Console.promptForOption("Would you like to add another item? [Y] Yes [N] No/n> ","x","y");

                }
            }
        }
        while(!userInput.equalsIgnoreCase("x"));
    }


}
