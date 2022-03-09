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
    private int statusTypeId;

    @NegativeOrZero(message = "amountFrom should be a negative or empty value.")
    private double amountFrom;

    @PositiveOrZero(message = "amountTo should be a positive or empty value.")
    private double amountTo;


    private double amount;


    public Transfer(){

    }

    public Transfer(int transferId, int transferTypeId, int statusTypeId,
                    double amountFrom, double amountTo, double amount) {

        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.statusTypeId = statusTypeId;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.amount = amount;
    }
}
