package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import exceptions.AccountNotFoundException;
import exceptions.AuthorizationException;

import java.math.BigDecimal;
import java.security.Principal;

public interface AccountDao {




    Account getAccountByUsername(String username) throws AccountNotFoundException;


}
