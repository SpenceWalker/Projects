package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import exceptions.AccountNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Account findAccountBalance(int userId, int accountId) throws AccountNotFoundException {

        String sql = "SELECT balance FROM account WHERE user_id = ? AND account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, accountId);

        if (results.next()){
            return mapRowToAccount(results);
        }

        throw new AccountNotFoundException();

    }


    @Override
    public Account findAccountUsingUserId(int userId) throws AccountNotFoundException {

        String sql = "SELECT account_id FROM account WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if (results.next()){
            return mapRowToAccount(results);
        }

        throw new AccountNotFoundException();
    }

    @Override
    public Account findUserUsingAccountId(int accountId) throws AccountNotFoundException {

        String sql = "SELECT user_id FROM account WHERE account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,accountId);

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
