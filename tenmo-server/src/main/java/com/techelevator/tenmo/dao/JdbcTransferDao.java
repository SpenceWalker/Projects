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

@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Transfer create(Transfer transfer,  int accountId)
                           throws TransferNotFoundException, AccountNotFoundException{

        Boolean doesAccountExist = doesAccountExist(accountId);
        if (doesAccountExist == null || !doesAccountExist){
            throw new AccountNotFoundException();
        }

         String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                      "VALUES              (?, ?, ?, ?, ?)";

         Integer transferId = jdbcTemplate.queryForObject(sql, Integer.class,
                 transfer.getTransferTypeId(),
                 transfer.getTransferStatusId(),
                 transfer.getAccountFrom(),
                 transfer.getAccountTo(),
                 transfer.getAmount());

         return get(transferId);

    }


    @Override
    public Transfer get(int transferId) throws TransferNotFoundException {

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

    @Override
    public Transfer update(Transfer transfer, int transferId, int id)
                          throws TransferNotFoundException {

        String sql = "UPDATE transfer " +
                "SET transfer_type_id = ?," +
                "transfer_status_id = ?," +
                "account_from = ?," +
                "account_to = ?," +
                "amount = ?" +
                "WHERE transfer_id = ?;";

        jdbcTemplate.update(sql,
                transfer.getTransferTypeId(),
                transfer.getTransferStatusId(),
                transfer.getAccountFrom(),
                transfer.getAccountTo(),
                transfer.getAmount(),
                transferId,
                id);

        //unsure of return

        return get(id);
    }

    @Override
    public Transfer getTypeId(int typeId) {


        return null;
    }

    @Override
    public Transfer getStatusId(int statusId) {
        return null;
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

    private Boolean doesAccountExist(int accountId){

        String sqlAccountExists = "SELECT EXISTS " +
                                  "(SELECT * " +
                                  "FROM account " +
                                  "WHERE account_id = ?);";

        return jdbcTemplate.queryForObject(sqlAccountExists, Boolean.class, accountId);
    }

}
