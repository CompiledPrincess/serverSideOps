package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("transfer/")
public class TransferController {
    private final AccountDao accountDao;
    private final TransferDao transferDao;
    private final UserDao userDao;

    public TransferController(AccountDao accountDao, TransferDao transferDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
        this.userDao = userDao;
    }

    @GetMapping("details/{transferId}")
    public Transfer getDetailsByTransferID(@PathVariable int transferId) {
        return transferDao.getDetailsByTransferID(transferId);
    }

    @GetMapping("all_transfers/{userId}")
    public List<Transfer> getAllTransfersByUserId(@PathVariable int userId) {
        return transferDao.getAllTransfersByUserId(userId);
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("change_existing_transfer/{transferId}")
    public boolean editTransferByTransferId(@PathVariable int transferId, @Valid @RequestBody Transfer newTransfer) {
        if (transferDao.editTransferByTransferId(transferId, newTransfer)) ;
        return true;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("new_transfer/")
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer) {
         return transferDao.createTransfer(transfer);

    }

}
