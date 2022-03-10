package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import exceptions.AccountNotFoundException;
import exceptions.TransferNotFoundException;

public interface TransferDao {


    public Transfer create(Transfer transfer, int accountId) throws TransferNotFoundException, AccountNotFoundException;

    public Transfer get(int transferId) throws TransferNotFoundException;

    public Transfer getTypeId(int typeId);

    public Transfer getStatusId(int statusId);

    public Transfer update(Transfer transfer, int transferId, int id) throws TransferNotFoundException;





}
