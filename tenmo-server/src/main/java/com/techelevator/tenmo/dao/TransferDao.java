package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {


    public Transfer create(Transfer transfer, int transferId);

    public Transfer getTransferById(int transferId);

    public Transfer getTypeId(int typeId);

    public Transfer getStatusId(int statusId);

    public Transfer update(Transfer transfer, int transferId);



}
