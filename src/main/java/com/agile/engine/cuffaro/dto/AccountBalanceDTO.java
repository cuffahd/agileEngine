package com.agile.engine.cuffaro.dto;

import java.io.Serializable;
import java.util.Date;

import com.agile.engine.cuffaro.model.AccountBalance;

public class AccountBalanceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921009037363709744L;

	private Double amount;
	
	private Date effectiveDate;
	
	public AccountBalanceDTO(AccountBalance accountBalance) {
		this.amount = accountBalance.getAmount();
		this.effectiveDate = accountBalance.getEffectiveDate();
	}

	public AccountBalanceDTO() {
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	

}
