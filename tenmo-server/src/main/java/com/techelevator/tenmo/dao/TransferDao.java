package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import exceptions.AccountNotFoundException;
import exceptions.TransferNotFoundException;

import java.util.List;

public interface TransferDao {


    public void createTransfer(Transfer transfer, String username) throws TransferNotFoundException, AccountNotFoundException;

    public List<Transfer> getTransfersSentReceived(String name) throws TransferNotFoundException;






}
