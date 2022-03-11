package com.techelevator.tenmo.controller;

import javax.validation.Valid;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.*;
import exceptions.AccountNotFoundException;
import exceptions.AuthorizationException;
import exceptions.TransferNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferDao transferDao;
    private final AccountDao accountDao;
    private final UserDao userDao;


    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int id, Principal principal)
            throws TransferNotFoundException, AccountNotFoundException {


        return transferDao.getTransfersSentReceived(id, principal.getName());
    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Transactional
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer, Principal principal)
            throws TransferNotFoundException, AuthorizationException, AccountNotFoundException {

        if (transfer.getAccountFrom() == transfer.getAccountTo()){
            throw new AuthorizationException();
        }

         Account account = accountDao.getAccountByUsername(principal.getName());

        if (transfer.getAccountFrom() != account.getAccountId()){
            throw new AuthorizationException();
        }

        if (transfer.getAmount() > account.getAccountBalance()){
            throw new AuthorizationException();
        }

        if (transfer.getAmount() <= 0){
            throw new AuthorizationException();
        }

        return transferDao.createTransfer(transfer, principal.getName());
    }





}
