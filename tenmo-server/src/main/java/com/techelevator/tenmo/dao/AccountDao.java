package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import exceptions.AccountNotFoundException;
import exceptions.AuthorizationException;

public interface AccountDao {


    Account updateAccount(Account account, int id) throws AccountNotFoundException;


    Account getAccountByUsername(String username) throws AccountNotFoundException;
}
