package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountServices{

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();


    private String authToken = null;
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Account getBalance(){

        Account getBalance = null;


        try {
            ResponseEntity<Account> response =
                    restTemplate.exchange(API_BASE_URL + "account/user", HttpMethod.GET, makeAuthEntity(), Account.class);

            getBalance = response.getBody();

        }catch (RestClientResponseException | ResourceAccessException e ) {
            BasicLogger.log(e.getMessage());
        }
        return getBalance;
    }


    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }


}
