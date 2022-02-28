package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Inventory{
    private List<Item> listOfItems = new ArrayList<>();
    private Item item;


    public Inventory(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public Inventory() {

    }

    public Item getItem() {
        return item;
    }

    public List<Item> getListOfItems() {
        return listOfItems;
    }

    public void addToInventory(Item item) {
        //Will change when we figure out the item class
        listOfItems.add(item);
    }
    public List<Item> createInventory(){

        File file = new File("vendingmachine.csv");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Item item = new Item();
                String line = scanner.nextLine();
                String[] words = line.split("\\|");
                item.setCodePoint(words[0]);
                item.setName(words[1]);
                item.setPrice(Double.parseDouble(words[2]));
                item.setType(words[3]);


                listOfItems.add(item);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } return listOfItems;
    }

}