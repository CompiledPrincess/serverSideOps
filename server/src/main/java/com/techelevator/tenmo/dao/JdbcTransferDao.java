package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcTransferDao implements TransferDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    //retrieve the details of any transfer based upon the transfer ID
    public Transfer getDetailsByTransferID(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    @Override
    //see all transfers user have sent or received
    public List<Transfer> getAllTransfersByUserId (int userId){
        List<Transfer> transfersByUserId = new ArrayList<>();
        String sql = "SELECT * FROM transfer JOIN account ON account_from = account_id " +
                "OR account_to = account_id WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()) {
            transfersByUserId.add(mapRowToTransfer(result));
        }
        return transfersByUserId;
    }

    @Override
    //Make a new transfer
    public Transfer createTransfer (Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferTypeId(), transfer.getTransferStatusId(),
                transfer.getAccountTo(), transfer.getAccountFrom(), transfer.getAmount());
        return getDetailsByTransferID(newId);
    }


    //The following method only applies to exiting transfers
    @Override
    public boolean editTransferByTransferId (int transferId, Transfer newTransfer) {
        String sql = "UPDATE transfer SET transfer_type_id = ?, transfer_status_id = ?, account_from = ?, " +
                "account_to = ?, amount = ? WHERE transfer_id = ?;";
        if (jdbcTemplate.update(sql, newTransfer.getTransferId(),newTransfer.getTransferStatusId(),
                newTransfer.getTransferTypeId(),newTransfer.getAmount(), newTransfer.getAccountFrom(),
                newTransfer.getAccountTo(), transferId) == 1 ) {
            System.out.println("Changes made to transfer");
        } else {
            throw new RuntimeException("Failed to make changes");
        }
        return true;
    }



    //Helper function
    private Transfer mapRowToTransfer (SqlRowSet rowSet){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rowSet.getInt("transfer_id"));
        transfer.setTransferTypeId(rowSet.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rowSet.getInt("transfer_status_id"));
        transfer.setAccountFrom(rowSet.getInt("account_from"));
        transfer.setAccountTo(rowSet.getInt("account_to"));
        transfer.setAmount(rowSet.getBigDecimal("amount"));
        return transfer;
    }

}
