package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {
    public List<Account> getAccounts();

    public Account getAccountByUserId(int userId);

    public BigDecimal viewBalanceByUserId(int userId);

    public boolean createNewAccount(Account newAccount);

    boolean updateBalance(int user_id,BigDecimal updatedAmount);
}
