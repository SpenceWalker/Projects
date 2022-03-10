package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import exceptions.AccountNotFoundException;
import exceptions.AuthorizationException;

public interface AccountDao {

    Account get(int id)throws AccountNotFoundException;

    Account findAccountBalance(int userId, int accountId) throws AccountNotFoundException;

    Account findAccountUsingUserId(int userId) throws AccountNotFoundException;

    Account findUserUsingAccountId(int accountId) throws AccountNotFoundException;

    Account updateAccount(Account account, int id)throws AccountNotFoundException;

    void deleteAccount(int id) throws AccountNotFoundException, AuthorizationException;
}
