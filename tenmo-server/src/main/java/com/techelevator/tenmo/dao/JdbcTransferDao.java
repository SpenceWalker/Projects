package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import exceptions.AccountNotFoundException;
import exceptions.TransferNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


public class JdbcTransferDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Transfer findTransferByTransferId(int transferId) throws TransferNotFoundException {

        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, " +
                             "account_from, account_to, amount " +
                     "FROM transfer " +
                     "WHERE transfer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,transferId);

            if (results.next()){
                return mapRowToTransfer(results);
        }
            throw new TransferNotFoundException();
    }

    private Transfer mapRowToTransfer(SqlRowSet row){
        Transfer transfer = new Transfer();

        transfer.setTransferTypeId(row.getInt("transfer_type_id"));
        transfer.setTransferStatusId(row.getInt("transfer_status_id"));
        transfer.setAccountFrom(row.getInt("account_from"));
        transfer.setAccountTo(row.getInt("account_to"));
        transfer.setAmount(row.getDouble("amount"));

        return transfer;
    }
}
