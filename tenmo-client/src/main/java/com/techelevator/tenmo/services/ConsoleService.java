package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.*;
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
        System.out.println("Select an id (that means select one of the numbers on the left hand side)");
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

        if (newTransfer == null){
            System.out.println("-------------------------------");
            System.out.println("Please enter the following");
            System.out.println("Account from, account to,  amount.");
            if (existingTransfer != null){
                System.out.println(existingTransfer);
            }else{
                System.out.println("Example: 2001, 2000, 500.00");
            }
            System.out.println("-----please god work------------");
            String userInput = (scanner.nextLine());
           String [] input = userInput.split(", ");
           newTransfer = new Transfer();

            newTransfer.setAccountFrom(Integer.parseInt(input[0]));
            newTransfer.setAccountTo(Integer.parseInt(input[1]));
            newTransfer.setAmount(Double.parseDouble(input[2]));
            newTransfer.setTransferTypeId(2);
            newTransfer.setTransferStatusId(2);

            System.out.println();
            if (newTransfer == null){
                System.out.println("invalid entry try again.");
            }
        }   return newTransfer;
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void printUserSelection(User[] users){
        if (users != null){
            System.out.println("---------------------");
            System.out.println("You may send money to the following list of people");
            System.out.println("--------------------------------------------------");
            System.out.println("(Send it to spencer)");
            System.out.println("---------------------");
            for (User user : users){
                System.out.println(user.getId() + ": " + user.getUsername());
            }
        }
    }

    public void printSingleUser(User user){
        System.out.println("if this works on the first try ill do a front flip");
        System.out.println("Here is a list of users to send money too.");
        if (user != null){
            System.out.println("-------------------------------------------");
            System.out.println("You should just send your money to spencer");
            System.out.println("-------------------------------------------");
            System.out.println("Id: " + user.getId());
            System.out.println("Username: " + user.getUsername());
        }

    }

    public void printTransferHistory(Transfer transfer){
        System.out.println("----please god work--------");
        System.out.println("Transfer history is as follows");
        System.out.println(transfer.getAccountFrom());
    }



    public void printBalance(Account account){
        System.out.println("----------------");
        System.out.println("Welcome valued customer");
        System.out.println("Account  details ");
            System.out.println("Account balance: " + account.getBalance());
        System.out.println("Account Id: " + account.getUserId());
        }
    }


