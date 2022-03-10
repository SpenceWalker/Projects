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
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Account get(int id) throws AccountNotFoundException {

        String sql = "SELECT *" +
                     "FROM account " +
                     "JOIN tenmo_user USING (user_id) " +
                     "WHERE account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()){
            return mapRowToAccount(results);
        }

        throw new AccountNotFoundException();
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


//    @Override
//    public Account get(int accountId, String userName) throws AccountNotFoundException {
//
//        String sql = "SELECT account.account_id, account.user_id, account.balance, tenmo_user.username " +
//                     "FROM account" +
//                     "JOIN tenmo_user ON account.user_id = tenmo_user.user_id" +
//                     "WHERE account_id = ?;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId, userName);
//
//        if (results.next()){
//            return mapRowToAccount(results);
//        }
//
//            throw new AccountNotFoundException();
//    }

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

    @Override
    public Account updateAccount(Account account, int id) throws AccountNotFoundException {

       String sql = "UPDATE account" +
               "SET user_id = ?," +
               "balance = ?" +
               "WHERE account_id = ?;";

       jdbcTemplate.update(sql,
               account.getUserId(),
               account.getAccountBalance(),
               id);

        return null;
    }

    @Override
    public void deleteAccount(int id) throws AccountNotFoundException, AuthorizationException {

        String sql = "DELETE" +
                     "FROM account" +
                     "WHERE account_id = ? ;";

        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected == 0){
            throw new AccountNotFoundException();
        }

    }


    private Account mapRowToAccount(SqlRowSet row) {

        Account account = new Account();

        account.setUserId(row.getInt("user_id"));
        account.setAccountId(row.getInt("account_id"));
        account.setAccountBalance(row.getDouble("balance"));

        return  account;

    }

}
