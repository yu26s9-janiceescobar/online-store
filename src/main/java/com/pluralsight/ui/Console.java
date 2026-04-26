package com.pluralsight.ui;
import java.util.*;
public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user for a string that matches one of the given options. Repeats until option is valid.
     * @param prompt the message display to the user.
     * @param options the options user must select from.
     * @return the String option the user has selected.
     */
    public static String promptForOption(String prompt, String... options){
        do {
            System.out.print(prompt);
            String userInput = scanner.nextLine().strip();
            for (String option : options) {
                if (userInput.equalsIgnoreCase(option)) {
                    return userInput;
                }
            }
            System.out.println("Enter a valid option");
        }
        while(true);
    }

    /**
     * The user is prompted to enter an integer between a given range.
     * @param prompt the message displayed to the user.
     * @param min the minimum range user is allowed to select integer from.
     * @param max the maximum range user is allowed to select integer from.
     * @return the integer user has selected.
     */
    public static int promptForInt(String prompt, int min, int max){
        String userInput;
        int parseInt;
        do {
            System.out.print(prompt);
            userInput = scanner.nextLine().strip();
            try {
                parseInt = Integer.parseInt(userInput);
                if (parseInt >= min && parseInt <= max) {
                    return parseInt;
                }
                System.out.println("Please Enter One of the options in the Menu");
            }catch(Exception e){
                System.out.println("Invalid Input");
            }
        }
        while (true);
    }

    /**
     * Displays message and prompts user for a string.
     * @param prompt the message displayed to user.
     * @return the String user has selected.
     */

    public static String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().strip();
    }
}
