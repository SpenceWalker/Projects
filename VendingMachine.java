package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class VendingMachine extends Inventory{
    public Map<String, Item> codePointAndItem = new TreeMap<>();
    public List<Item> inventory = new ArrayList<>(super.createInventory());

    public VendingMachine(Map<String, Item> codePointAndItem){
        this.codePointAndItem = codePointAndItem;
    }

    public VendingMachine(){

    }
    public void returnAudit(Person customer) {
        File auditFile = new File("log.txt");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
        {
            try (FileWriter fileWriter = new FileWriter(auditFile, true)) {
                BufferedReader buffRead = new BufferedReader(new FileReader("log.txt"));
                PrintWriter writer = new PrintWriter(fileWriter);
                writer.println(format.format(date) + " feed money " + " $ " + String.format("%.2f",customer.getMoneyFed()) + " " + " $ " + String.format("%.2f",customer.getCurrentMoneyProvided()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void purchaseAudit(Item item, Person customer) {
        File auditFile = new File("log.txt");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
        {
            try (FileWriter fileWriter = new FileWriter(auditFile, true)) {
                BufferedReader buffRead = new BufferedReader(new FileReader("log.txt"));
                PrintWriter writer = new PrintWriter(fileWriter);
                writer.println(format.format(date) + item.getName() + " "  + item.getCodePoint()  + " $ " + String.format("%.2f",customer.getCurrentMoneyProvided()) + " $ " + String.format("%.2f",(customer.getCurrentMoneyProvided() - item.getPrice())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void returnMoneyAudit(Item item, Person customer) {
        File auditFile = new File("log.txt");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
        {
            try (FileWriter fileWriter = new FileWriter(auditFile, true)) {
                BufferedReader buffRead = new BufferedReader(new FileReader("log.txt"));
                PrintWriter writer = new PrintWriter(fileWriter);
                writer.println(format.format(date) + "WALT'S CHANGE " + "$" + String.format("%.2f",customer.getCurrentMoneyProvided()) +  " $ "  + "$0.00");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public Map<String, Item> displayInventory() {
        for (Item item : inventory) {
            codePointAndItem.put(item.getCodePoint(), item);
        }
        for(String codePoint : codePointAndItem.keySet()){
            String name = codePointAndItem.get(codePoint).getName();
            double price = codePointAndItem.get(codePoint).getPrice();
            int stock = codePointAndItem.get(codePoint).getStock();


            System.out.println(codePoint + ") " + name + " || Price: $" + String.format("%.2f", price) + " || Quantity left: " + stock);
        }
        return codePointAndItem;
    }

    public void dispenseItem(Item item){
        System.out.println("'" + item.getName() + "'  Cost: $" + item.getPrice());
//        item.setStock(item.getStock() - 1);
        if(item.getType().equalsIgnoreCase("chip")){
            System.out.println("Crunch Crunch, Walt!");
        }else if(item.getType().equalsIgnoreCase("candy")){
            System.out.println("Munch Munch, Walt!");
        }else if(item.getType().equalsIgnoreCase("drink")){
            System.out.println("Glug Glug, Walt!");
        }else if(item.getType().equalsIgnoreCase("gum")){
            System.out.println("Chew Chew, Walt!");
        }

    }
    public void updateStock(Item item){
        if(item.getStock() <= 0){
            System.out.println("This is sold out!");
        }
        item.setStock(item.getStock() - 1);

    }

    public void returnChange(Person customer){
        double quarter = 0.25;
        double dime = 0.10;
        double nickel = 0.05;
        double penny = 0.01;
        int amountOfQuarters = 0;
        int amountOfDimes = 0;
        int amountOfNickels = 0;
        int amountOfPennies = 0;



        double money = customer.getCurrentMoneyProvided();

        while(money > 0) {
            if (!(money % quarter > 0)) {
                money = money - quarter;
                amountOfQuarters++;
            }else if (!(money % dime > 0)) {
                money = money - dime;
                amountOfDimes++;
            }else if (!(money % nickel == 0)) {
                money = money - nickel;
                amountOfNickels++;
            }else if (!(money % penny == 0)) {
                money = money - penny;
                amountOfPennies++;
            }
        }

        System.out.println("Here is " + amountOfQuarters + " Quarters, " + amountOfDimes + " dimes, "
                + amountOfNickels + " nickels, " + amountOfPennies + " pennies.");
    }

}