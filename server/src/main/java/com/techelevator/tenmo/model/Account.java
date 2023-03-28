package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    @NotBlank
    private int accountId;

    @NotBlank(message = "Please provide user name")
    private int userId;

    @Positive (message = "Balance must be a positive number")
    private BigDecimal balance;

    public Account(){

    }

    //getters
    public int getAccountId() { return accountId; }
    public int getUserId() { return userId; }
    public BigDecimal getBalance() { return balance; }

    //setters
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

}
