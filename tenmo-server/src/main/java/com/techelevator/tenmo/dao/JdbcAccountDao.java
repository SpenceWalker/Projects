package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import exceptions.AccountNotFoundException;
import exceptions.AuthorizationException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Account getAccountByUsername(String username) throws AccountNotFoundException {

        String sql = "SELECT account.account_id, account.user_id, account.balance " +
                    "FROM account " +
                    "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                    "WHERE username = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);

        if (results.next()){
            return mapRowToAccount(results);
        }

        throw new AccountNotFoundException();
    }



    private Account mapRowToAccount(SqlRowSet row) {

        Account account = new Account();

        account.setUserId(row.getInt("user_id"));
        account.setAccountId(row.getInt("account_id"));
        account.setAccountBalance(row.getDouble("balance"));

        return  account;

    }

}
