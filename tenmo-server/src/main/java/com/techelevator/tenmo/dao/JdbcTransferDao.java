package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Transfer;
import exceptions.AccountNotFoundException;
import exceptions.TransferNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public void createTransfer(Transfer transfer,  String username)
            throws AccountNotFoundException, TransferNotFoundException {

        Boolean doesAccountExist = doesAccountExist(transfer.getAccountTo(),transfer.getAccountFrom());
        if (doesAccountExist == null || !doesAccountExist){
            throw new AccountNotFoundException();
        }

         String sql =
                              "BEGIN TRANSACTION;" +
                              "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                      "VALUES              (?, ?, ?, ?, ?);" +
                      "UPDATE               account " +
                      "SET                  balance = balance - ? " +
                      "WHERE                account_id = ?;" +
                      "UPDATE               account " +
                      "SET                  balance = balance + ? " +
                      "WHERE                account_id = ?;" +
                                      "COMMIT;";



         jdbcTemplate.update(sql,
                 transfer.getTransferTypeId(),
                 transfer.getTransferStatusId(),
                 transfer.getAccountFrom(),
                 transfer.getAccountTo(),
                 transfer.getAmount(),
                 transfer.getAmount(),
                 transfer.getAccountFrom(),
                 transfer.getAmount(),
                 transfer.getAccountTo());

    }


    @Override
    public List<Transfer> getTransfersSentReceived(int userId, String name)
                                                   throws TransferNotFoundException {

        List<Transfer> transfers = new ArrayList<>();

        String sql = "SELECT to_user.username, from_user.username, transfer.transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, " +
                "transfer.account_from, transfer.account_to, transfer.amount " +
                "FROM transfer " +
                "JOIN account AS acc_from ON transfer.account_from = acc_from.account_id " +
                "JOIN account AS acc_to ON transfer.account_from = acc_to.account_id " +
                "JOIN tenmo_user AS from_user ON acc_from.user_id = from_user.user_id " +
                "JOIN tenmo_user AS to_user ON acc_to.user_id = to_user.user_id " +
                "WHERE acc_from.user_id = 1001 AND acc_to.account_id = 1002;";



    }




    private Transfer mapRowToTransfer(SqlRowSet row){
        Transfer transfer = new Transfer();

        transfer.setTransferId(row.getInt("transfer_id"));
        transfer.setTransferTypeId(row.getInt("transfer_type_id"));
        transfer.setTransferStatusId(row.getInt("transfer_status_id"));
        transfer.setAccountFrom(row.getInt("account_from"));
        transfer.setAccountTo(row.getInt("account_to"));
        transfer.setAmount(row.getDouble("amount"));

        return transfer;
    }

    private Boolean doesAccountExist(int accountId, int accountFrom){

        String sqlAccountExists = "SELECT EXISTS " +
                                  "(SELECT * " +
                                  "FROM account " +
                                  "WHERE account_id = ?);";

        return jdbcTemplate.queryForObject(sqlAccountExists, Boolean.class, accountId);
    }

}
