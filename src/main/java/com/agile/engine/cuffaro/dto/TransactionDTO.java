package com.agile.engine.cuffaro.dto;

import java.io.Serializable;
import java.util.Date;

import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.model.TransactionItem;

public class TransactionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7929394647140356419L;

	/**
	 * Unique transaction identifier, generated by the service, when the transaction is being stored.
	 */
	private String transactionId;
	
	private TransactionTypeEnum transactionType;
	
	/**
	 * Transaction numeric value. Incrementing or decrementing the account balance, based on the transaction type.
	 */
	private Double amount;
	
	/**
	 * Effective date-time, generated by the service, when the transaction is being stored. 
	 */
	private Date effectiveDate;

	public TransactionDTO(String transactionId, TransactionTypeEnum transactionType, Double amount,
			Date effectiveDate) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.effectiveDate = effectiveDate;
	}
	
	public TransactionDTO(TransactionItem item) {
		this(item.getTransactionId(), item.getTransactionType(), item.getAmount(), item.getEffectiveDate());
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		TransactionDTO other = (TransactionDTO) obj;
		if (transactionId == null) {
			if (other.transactionId != null) {
				return false;
			}
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (transactionType != other.transactionType) {
			return false;
		}
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
