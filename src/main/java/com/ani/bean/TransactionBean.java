package com.ani.bean;

import java.io.Serializable;
import java.sql.Date;

public class TransactionBean implements Serializable
{
	private int txn_id = (int)(Math.random() * 90000) + 10000; 
	private Date txn_date;
	private String senderAccount;
    private String receiverAccount;
	private String description, txn_type;
	private Double amount;
	private String status;
	
	public TransactionBean()
	{
		
	}

	public int getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(int txn_id) {
		this.txn_id = txn_id;
	}

	public Date getTxn_date() {
		return txn_date;
	}

	public void setTxn_date(Date txn_date) {
		this.txn_date = txn_date;
	}
	
	public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTxn_type() {
		return txn_type;
	}

	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
