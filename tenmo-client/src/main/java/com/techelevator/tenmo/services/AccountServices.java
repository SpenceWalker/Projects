package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountServices extends Account{

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();


    public Account getBalance(Account accountId){

        Account getBalance = null;

        try {
            getBalance = restTemplate.getForObject(API_BASE_URL + "/account/user" + accountId, Account.class);
        }catch (RestClientResponseException | ResourceAccessException e ) {
            BasicLogger.log(e.getMessage());
        }
        return getBalance;
    }



}
