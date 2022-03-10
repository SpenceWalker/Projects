package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import exceptions.TransferNotFoundException;

public interface TransferDao {


    public Transfer create(Transfer transfer) throws TransferNotFoundException;

    public Transfer get(int transferId) throws TransferNotFoundException;

    public Transfer getTypeId(int typeId);

    public Transfer getStatusId(int statusId);

    public Transfer update(Transfer transfer, int transferId) throws TransferNotFoundException;





}
