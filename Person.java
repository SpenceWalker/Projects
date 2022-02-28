package com.techelevator;

import java.util.List;
import java.util.Scanner;

public class Person {
    private double currency;
    private double currentMoneyProvided;
    private double moneyFed;

    public Person(double currency){
        this.currency = currency;

    }
    public Person(){

    }

    public void setCurrentMoneyProvided(double currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public double getCurrentMoneyProvided() {return currentMoneyProvided;}

    public double getMoneyFed() {
        return moneyFed;
    }

    public void feedMoney(int moneyFed) {
        this.moneyFed = moneyFed;
        while (moneyFed <= 10) {
            if((moneyFed == 1 || moneyFed == 2 || moneyFed == 5 || moneyFed == 10)) {
                currentMoneyProvided += moneyFed;
                if (currentMoneyProvided > 30) {
                    currentMoneyProvided = currentMoneyProvided - moneyFed;
                    System.out.println("You spent too much already.");
                    break;
                }
            }else{
                System.out.println("$" + moneyFed + " is not a real bill.");
            }
            break;
        }


    }

}