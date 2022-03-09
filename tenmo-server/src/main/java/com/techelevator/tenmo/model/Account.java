package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

public class Account {


    @Min(value = 1, message = "accountId should be a positive integer value.")
    private int accountId;

    @Min(value = 1, message = "userId should be a positive integer value.")
    private int userId;

    @PositiveOrZero(message = "accountBalance can't be a negative value.")
    private double accountBalance;

    public Account(){

    }

    public Account(int accountId, int userId, double accountBalance){

        this.accountBalance = accountBalance;
        this.accountId = accountId;
        this.userId = userId;

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
