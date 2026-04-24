package com.pluralsight.ui;
import java.util.*;
public class Console {
    public static Scanner scanner = new Scanner(System.in);
    public static void errorMessage(){
        System.out.println("Oops! Invalid Input :( Please Try Again.");
    }
    public static boolean isInt(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static int promptForInt(String prompt){
        boolean isInt;
        String userInput;
        do {
            System.out.print(prompt);
            userInput = scanner.nextLine();
            isInt = isInt(userInput);
        }
        while (!isInt);
        return Integer.parseInt(userInput);

    }
    public static String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
