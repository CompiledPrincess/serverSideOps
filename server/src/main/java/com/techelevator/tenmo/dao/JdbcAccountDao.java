package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcAccountDao(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    //Get a list of existing accounts
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            accounts.add(mapRowToAccount(result));
        }
        return accounts;
    }

    @Override
    //Retrieve account by user ID
    public Account getAccountByUserId(int userId) {
        Account account = null;
        String sql = "SELECT * FROM account WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if (result.next()) {
            account = mapRowToAccount(result);
        } else {
            throw new RuntimeException("Account failed");
        }
        return account;
    }

    @Override
    //View balance by user ID
    public BigDecimal viewBalanceByUserId(int userId) {
        String sql = "SELECT balance FROM account WHERE user_id = ? ";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
    }

    @Override
    //Create new account
    public boolean createNewAccount(Account newAccount) {
        String sql = "INSERT INTO account (user_id, balance) VALUES (?, ?);" ;
        if (jdbcTemplate.update(sql, newAccount.getAccountId()) == 1) {
            System.out.println("New account created");
        } else {
            throw new RuntimeException("Failed to create new account");
        }
        return true;
    }

    @Override
    //Update balance
    public boolean updateBalance(int user_id, BigDecimal updatedAmount) {
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        if (jdbcTemplate.update(sql,updatedAmount, user_id)== 1) {
            System.out.println("Balance updated");
        }
        return true;
    }




    //Helper function
    private Account mapRowToAccount(SqlRowSet rowSet){
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }

}



