package com.agile.engine.cuffaro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AccountBalance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7013442732635173622L;

	@Id
	@Column(nullable = false)
	private Long accountId = 1L; 
	
	@Column(nullable = false)
	private Double amount;
	
	/**
	 * Effective date-time, generated by the service, when the transaction is being stored. 
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date effectiveDate;

	public AccountBalance() {}
	public AccountBalance(Long accountId, Double amount) {
		this.accountId = accountId;
		this.amount = amount;
	}
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	public void reduceAmount(Double otherAmount) {
		this.amount = this.amount - otherAmount;
		
	}
	
	public void increaseAmount(Double otherAmount) {
		this.amount = this.amount + otherAmount;
		
	}
	
}
