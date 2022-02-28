package com.techelevator;

import com.sun.security.jgss.GSSUtil;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class VendingMachineUI {
    Scanner scanner =  new Scanner(System.in);
    Person customer = new Person(0);
    VendingMachine vendingMachine = new VendingMachine();
    Map<String, Item> purchaseMenu = new TreeMap<>();
    Inventory inventory = new Inventory();


    public void displayMainMenu() {
        Inventory inventory = new Inventory();
        List<Item> listOfItems = new ArrayList<>();
        listOfItems.addAll(inventory.createInventory());

        System.out.println();
        System.out.println("Welcome to The Vendo-Matic 800!");
        System.out.println("> (1) Display Vending Machine Items");
        System.out.println("> (2) Purchase");
        System.out.println("> (3) Exit");
        System.out.print("What would you like to do? (Type the number):");

        String userChoice = scanner.nextLine();
        if(userChoice.isEmpty()){
            System.out.println("Quit trying to break me!");
            System.out.println("Type 1, 2 OR 3");
            displayMainMenu();
        }

        if (userChoice.equalsIgnoreCase("1")) {
            vendingMachine.displayInventory();
            displayMainMenu();

        } else if (userChoice.equalsIgnoreCase("2")) {
            displayPurchaseProcessingMenu();

        } else if (userChoice.equalsIgnoreCase("3")) {
            System.out.println("Goodbye.");
            System.exit(1);
        }
        if (!userChoice.equalsIgnoreCase("1") && !userChoice.equalsIgnoreCase("2") && !userChoice.equalsIgnoreCase("3")) {
            System.out.println("Quit trying to break me...");
            System.out.println("Type 1, 2 OR 3");
            displayMainMenu();
        }
    }
    public void displayPurchaseProcessingMenu() {
        System.out.println();
        System.out.println("*** PURCHASE PROCESSING ***");
        System.out.println(">(1) Feed Money ($30 LIMIT)");
        System.out.println(">(2) Select Product");
        System.out.println(">(3) Finish Transaction");
        System.out.println();
        System.out.println("Spendable Money: $" + String.format("%.2f", customer.getCurrentMoneyProvided()));
        System.out.print("What would you like to do? (Type the number):");

        String userChoice = scanner.nextLine();
        String userDollar = "";

        if(userChoice.isEmpty()){
            System.out.println("Quit trying to break me!");
            System.out.println("Type 1, 2 OR 3");
            displayPurchaseProcessingMenu();
        }

        if (userChoice.equalsIgnoreCase("1")) {
            String yesOrNo = "";

            System.out.println("What dollar bill are you putting in? (1, 2, 5 or 10) ");

            try {
                userDollar = scanner.nextLine();
                int dollarBill = Integer.parseInt(userDollar);
                if (dollarBill > 10) {
                    System.out.println("Enter a smaller bill.");
                    displayPurchaseProcessingMenu();
                }
                if (dollarBill == 1 || dollarBill == 2 || dollarBill == 5 || dollarBill == 10) {
                    customer.feedMoney(dollarBill);
                    vendingMachine.returnAudit(customer);
                    System.out.println("Spendable Money: $" + String.format("%.2f", customer.getCurrentMoneyProvided()));
                    do {
                        System.out.print("Would you like to add more money? (Y/N)");
                        yesOrNo = scanner.nextLine();
                        if (yesOrNo.isEmpty()) {
                            System.out.println("You didn't enter anything..");
                            System.out.println("Enter 1, 2, 5 or 10.");
                        }
                        if (!yesOrNo.equalsIgnoreCase("y")) {
                            System.out.println("Back to the menu!");
                            displayPurchaseProcessingMenu();
                            break;
                        }
                        System.out.print("What dollar bill are you putting in? (1, 2, 5 or 10) ");
                        userDollar = scanner.nextLine();
                        dollarBill = Integer.parseInt(userDollar);
                        customer.feedMoney(dollarBill);
                        vendingMachine.returnAudit(customer);
                        System.out.println("Spendable Money: $" + String.format("%.2f", customer.getCurrentMoneyProvided()));

                    } while (yesOrNo.equalsIgnoreCase("y"));
                } else {
                    System.out.println("Enter a valid bill. (1, 2, 5, 10)");
                    displayPurchaseProcessingMenu();
                }
            }catch (NumberFormatException e){
                System.out.println("Quit trying to break me..");
                System.out.println("Enter either 1, 2, 5 or 10");
                displayPurchaseProcessingMenu();
            }catch (InputMismatchException e){
                System.out.println("Quit trying to break me..");
            }
        } else if (userChoice.equalsIgnoreCase("2")) {
            purchaseMenu.putAll(vendingMachine.displayInventory());


            System.out.println();
            System.out.println("What do you want to buy? Select an option between A1 --> D4: ");
            System.out.println("Spendable Money: $" + String.format("%.2f", customer.getCurrentMoneyProvided()));
            String pickedItem = scanner.nextLine();

            if(pickedItem.isEmpty()){
                System.out.println("You didn't pick anything!");
//                displayPurchaseProcessingMenu();
            }
            for (String choice : purchaseMenu.keySet()) {

                if (pickedItem.equalsIgnoreCase(choice)) {
                    if (customer.getCurrentMoneyProvided() < purchaseMenu.get(choice).getPrice()) {
                        System.out.println("Get your money up.");
                        System.out.println("You have: $" + String.format("%.2f", customer.getCurrentMoneyProvided()));
                        System.out.println("You need: $" + String.format("%.2f", purchaseMenu.get(choice).getPrice()));
                        displayPurchaseProcessingMenu();
                    }
                    if(purchaseMenu.get(choice).getStock() <= 0){
                        System.out.println("This is sold out!");
                        displayPurchaseProcessingMenu();
                    }
                    vendingMachine.dispenseItem(purchaseMenu.get(choice)); //prints name and price
                    vendingMachine.purchaseAudit(purchaseMenu.get(choice), customer);
                    double moneyRemaining = customer.getCurrentMoneyProvided() - purchaseMenu.get(choice).getPrice();
                    customer.setCurrentMoneyProvided(moneyRemaining);
                    vendingMachine.updateStock(purchaseMenu.get(choice));
                    displayPurchaseProcessingMenu();


                }
            }
            if (!purchaseMenu.containsKey(pickedItem)) {
                System.out.println("Item doesn't exist");
                displayPurchaseProcessingMenu();
            }
        }
        else if (userChoice.equalsIgnoreCase("3")) {

            System.out.println("Change to be given back: " +  String.format("%.2f", customer.getCurrentMoneyProvided()));
            vendingMachine.returnMoneyAudit(inventory.getItem(), customer);
            vendingMachine.returnChange(customer);
            customer.setCurrentMoneyProvided(0);
            displayMainMenu();

        }
        else if(!userChoice.equalsIgnoreCase("1") && !userChoice.equalsIgnoreCase("2") && !userChoice.equalsIgnoreCase("3")){
            System.out.println("Quit trying to break me...");
            System.out.println("Type 1, 2 or 3");
            displayPurchaseProcessingMenu();
        }


    }
}