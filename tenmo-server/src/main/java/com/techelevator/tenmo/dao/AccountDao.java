package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import exceptions.AccountNotFoundException;

public interface AccountDao {

    Account findAccountBalance(int userId, int accountId) throws AccountNotFoundException;

    Account findAccountUsingUserId(int userId) throws AccountNotFoundException;

    Account findUserUsingAccountId(int accountId) throws AccountNotFoundException;

}
