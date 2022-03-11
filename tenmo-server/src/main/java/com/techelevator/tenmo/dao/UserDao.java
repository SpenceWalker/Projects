package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;
import exceptions.UserNotFoundException;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, double initialBalance);

    User findByUserId(int id) throws UserNotFoundException;
}
