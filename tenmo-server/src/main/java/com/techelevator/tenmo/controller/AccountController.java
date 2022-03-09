package com.techelevator.tenmo.controller;

import javax.validation.Valid;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import exceptions.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.LoginDTO;
import com.techelevator.tenmo.model.RegisterUserDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.web.server.ResponseStatusException;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountDao accountDao;
    private final UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;

    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public Account getAccount(@Valid @PathVariable int userId) throws AccountNotFoundException {
        return accountDao.findAccountUsingUserId(userId);
    }

    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public Account getBalance(@Valid @PathVariable int accountId, int userId) throws AccountNotFoundException {
        return accountDao.findAccountBalance(accountId, userId);
    }

    @RequestMapping(path = "/{accountId}", method = RequestMethod.GET)
    public Account getUser(@Valid @PathVariable int accountId) throws AccountNotFoundException {
        return accountDao.findUserUsingAccountId(accountId);
    }
}
