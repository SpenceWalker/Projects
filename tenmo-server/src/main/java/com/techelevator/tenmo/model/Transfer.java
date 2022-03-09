package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.PositiveOrZero;

public class Transfer {

    @Min(value = 1, message = "transferId should be a positive integer value.")
    private int transferId;

    @Min(value = 1, message = "transferTypeId should be a positive integer value.")
    private int transferTypeId;

    @Min(value = 1, message = "statusTypeId should be a positive integer value.")
    private int transferStatusId;

    @Min(value = 1, message = "account number should be positive integer value.")
    private int accountFrom;

    @Min(value = 1, message = "account number be a positive integer value")
    private int accountTo;

    @PositiveOrZero(message = "amount should be a positive value")
    private double amount;


    public Transfer(){
    }

    public Transfer(int transferId, int transferTypeId, int transferStatusId,
                    int accountFrom, int accountTo, double amount) {

        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
