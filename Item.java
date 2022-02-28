package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Item extends Inventory{
    private String codePoint;
    private String name;
    private double price;
    private String type;
    public int stock = 5;

    public Item(String codePoint, String name, double price, String type, int stock){
        this.codePoint = codePoint;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }
    public Item() {

    }

    public String getCodePoint() {return codePoint;}

    public void setCodePoint(String codePoint) {this.codePoint = codePoint;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public double getPrice() { return price; }

    public void setPrice(double price) {this.price = price;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public int getStock(){return stock;}

    public void setStock(int stock) {this.stock = stock;}

    public void displayNameAndStock(){
        Map<String, Integer> nameAndStock = new TreeMap<>();

        //Want to be sorted according to codePoint
        File file = new File("vendingmachine.csv");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\|");
                nameAndStock.put(words[1], 5);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        for(String key : nameAndStock.keySet()){
            System.out.println("("+key+")" + " Amount left: " + nameAndStock.get(key));
        }
    }
    public void displayPrice(){
        super.createInventory();
        List<Item> items = super.getListOfItems();
    }
    public int updateStock(Item item){
        item.setStock(item.getStock() - 1);
        return stock;

    }
}