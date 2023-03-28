package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class Transfer {
    //Member variables
    private int transferId;
    private int transferTypeId;
    private int transferStatusId;

    //Constrains
  @NotBlank(message = "Account cannot be blank")
  private int accountFrom;

   @NotBlank(message = "Account cannot be blank")
   private int accountTo;

  @Positive(message = "Transfer amount cannot be negative")
  private BigDecimal amount;

    public Transfer() {
    }

    //getters
    public int getTransferId() {
        return transferId;
    }
    public int getAccountFrom() {
        return accountFrom;
    }
    public int getAccountTo() {
        return accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }
    public int getTransferStatusId() {
        return transferStatusId;
    }

    //setters
    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }
    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }
    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }
    public void setTransferTypeId(int transferTypeId) { this.transferTypeId = transferTypeId; }
    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public void setAmount(BigDecimal amount) {this.amount = amount;}


}
