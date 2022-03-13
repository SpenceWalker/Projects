package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class UserService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();



    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    public User getUserById(int userId){

        User user  = null;

        try {
            ResponseEntity<User>response =
                    restTemplate.exchange(API_BASE_URL + "users/" + userId, HttpMethod.GET, makeAuthEntity(), User.class);

            user = response.getBody();


        }catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public User[] getListOfAllUsers(){

        User[] users = null;

        try {
            ResponseEntity<User[]> response =
                    restTemplate.exchange(API_BASE_URL + "users", HttpMethod.GET, makeAuthEntity(), User[].class);
                    users = response.getBody();

        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
