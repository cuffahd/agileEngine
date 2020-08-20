package com.agile.engine.cuffaro.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.agile.engine.cuffaro.model.AccountBalance;

public class AccountBalanceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921009037363709744L;

	private BigDecimal amount;
	
	private Date effectiveDate;
	
	public AccountBalanceDTO(AccountBalance accountBalance) {
		this.amount = accountBalance.getAmount();
		this.effectiveDate = accountBalance.getEffectiveDate();
	}

	public AccountBalanceDTO() {
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AccountBalanceDTO other = (AccountBalanceDTO) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (effectiveDate == null) {
			if (other.effectiveDate != null) {
				return false;
			}
		} else if (!effectiveDate.equals(other.effectiveDate)) {
			return false;
		}
		return true;
	}
	

}
