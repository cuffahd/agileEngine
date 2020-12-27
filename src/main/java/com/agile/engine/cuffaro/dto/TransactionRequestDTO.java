package com.agile.engine.cuffaro.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.model.TransactionItem;

public class TransactionRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1582514313584568885L;
	
	private TransactionTypeEnum transactionType;
	private BigDecimal amount;
	
	public TransactionRequestDTO() {
		//Default constructor without parameters
	}
	
	public TransactionRequestDTO(TransactionTypeEnum transactionType, BigDecimal amount) {
		this.transactionType = transactionType;
		this.amount = amount;
	}
	
	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.toString().hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}

	public TransactionItem toTransaction() {
		return new TransactionItem(this.transactionType, this.amount);
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
		TransactionRequestDTO other = (TransactionRequestDTO) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (transactionType != other.transactionType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TransactionRequestDTO [type=" + transactionType + ", amount=" + amount + "]";
	}
}
