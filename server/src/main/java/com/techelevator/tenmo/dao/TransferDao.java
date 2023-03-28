package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    public List<Transfer> getAllTransfersByUserId(int userId);

    public Transfer getDetailsByTransferID(int transferId);

   public boolean editTransferByTransferId(int userId, Transfer newTransfer);

   public Transfer createTransfer(Transfer transfer);
}
