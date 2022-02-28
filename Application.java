package com.techelevator;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		inventory.createInventory();
		VendingMachineUI mainMenu = new VendingMachineUI();
		mainMenu.displayMainMenu();
	}
}