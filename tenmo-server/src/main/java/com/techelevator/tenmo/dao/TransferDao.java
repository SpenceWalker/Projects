package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import exceptions.AccountNotFoundException;
import exceptions.TransferNotFoundException;

public interface TransferDao {


    public Transfer createTransfer(Transfer transfer, String username) throws TransferNotFoundException, AccountNotFoundException;

    public Transfer getTransfersSentReceived(int transferId, String name) throws TransferNotFoundException;






}
