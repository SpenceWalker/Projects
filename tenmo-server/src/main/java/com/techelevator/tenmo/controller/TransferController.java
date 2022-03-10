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
                                throws TransferNotFoundException{

        return transferDao.get(id);
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.GET)
    public Transfer getTypeId(@PathVariable int typeId, Principal principal)
                             throws TransferNotFoundException{

        return transferDao.getTypeId(typeId);
    }

    @RequestMapping(path = "/{statusId}", method = RequestMethod.GET)
    public Transfer getStatusId(@PathVariable int statusId, Principal principal)
                                throws TransferNotFoundException{

        return transferDao.getStatusId(statusId);
    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Transactional
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer, Principal principal)
            throws TransferNotFoundException, AuthorizationException {

        if (transfer.getAccountFrom() == transfer.getAccountTo()){
            throw new AuthorizationException();
        }


        return transferDao.create(transfer);
    }


    @RequestMapping(path =  "/{id}",method = RequestMethod.PUT)
    public Transfer updateTransfer(@Valid @RequestBody Transfer transfer, @PathVariable int id,
                                   Principal principal)throws TransferNotFoundException{

        transfer.setTransferId(id);

        return transferDao.update(transfer, id);
    }
}
