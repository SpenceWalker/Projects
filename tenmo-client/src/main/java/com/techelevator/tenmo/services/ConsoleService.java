package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {

    public UserService userService = new UserService();

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public int promptForUserId(){
        System.out.println("Select an id");
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please don't do that");
            }
        }
    }


    public Transfer promptForTransferData(){
        return promptForTransferData(null);
    }

    public Transfer promptForTransferData(Transfer existingTransfer){
        Transfer newTransfer = null;

        while (newTransfer == null){
            System.out.println("-------------------------------");
            System.out.println("Please enter the following");
            System.out.println("Transfer ID, Transfer Type ID, transfer status ID, account from, account to,  amount.");
            if (existingTransfer != null){
                System.out.println(existingTransfer);
            }else{
                System.out.println("Example: 3001, 2, 2, 2001, 2000, 500.00");
            }
            System.out.println("-----please god work------------");
            System.out.println();
            if (newTransfer == null){
                System.out.println("invalid entry try again.");
            }else if (newTransfer != null){
                newTransfer.setTransferId(existingTransfer.getTransferId());
            }
        }   return newTransfer;
    }





//
//    public BigDecimal promptForBigDecimal(String prompt) {
//        System.out.print(prompt);
//        while (true) {
//            try {
//                return new BigDecimal(scanner.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a decimal number.");
//            }
//        }
//    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void printAllUsers(User[] users){
        System.out.println("Here is a list of all available users to send money to");
    }

    public void printBalance(Account accounts){
        System.out.println("----------------");
        System.out.println("Welcome valued customer");
        System.out.println("Account Balance details ");
        if (accounts == null){
            System.out.println("No account could be found, please try again.");
        }else {
            System.out.println("Account Id: " + accounts.getAccountId());
            System.out.println("User Id: " + accounts.getUserId());
            System.out.println("Account balance: " + accounts.getBalance());
        }
    }
}
