package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import okhttp3.Credentials;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

public class UserService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();





//
//        ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "/users/", HttpMethod.GET, entity, User[].class);
//
//        return response.getbody;
//    }

}
