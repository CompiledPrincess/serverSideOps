package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("account/")
public class AccountController {
    private final AccountDao accountDao;
    private final UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountDao.getAccounts();
    }

    @GetMapping("{Id}")
    public Account getAccountByUserId(@PathVariable int Id) {
        return accountDao.getAccountByUserId(Id);
    }

    @GetMapping("{Id}/balance")
    public BigDecimal viewBalanceByUserId(@PathVariable int Id) {
        return accountDao.viewBalanceByUserId(Id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public boolean createNewAccount(@Valid @RequestBody Account newAccount) {
        return accountDao.createNewAccount(newAccount);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("update_balance/{id}")
        public boolean addBalance (@Valid @RequestBody Account account, @PathVariable int id) {
        return accountDao.updateBalance(id, account.getBalance());
    }



}
