package com.agile.engine.cuffaro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.agile.engine.cuffaro.enums.TransactionTypeEnum;

@Entity
public class TransactionItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220884298634069949L;


	/**
	 * Unique transaction identifier, generated by the service, when the transaction is being stored.
	 */
	@Id
	@GeneratedValue (generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String transactionId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransactionTypeEnum transactionType;
	
	/**
	 * Transaction numeric value. Incrementing or decrementing the account balance, based on the transaction type.
	 */
	@Column(nullable = false)
	private Double amount;
	
	/**
	 * Effective date-time, generated by the service, when the transaction is being stored. 
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date effectiveDate;

	@PrePersist
	void effectiveDate() {
		this.effectiveDate= new Date();
	}
	
	public TransactionItem() {}
	
	public TransactionItem(TransactionTypeEnum transactionType, Double amount) {
		this.transactionType = transactionType;
		this.amount = amount;
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
		TransactionItem other = (TransactionItem) obj;
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
		return true;
	}
	
	@Override
	public String toString() {
		return "TransactionItem [transactionId=" + transactionId + ", transactionType=" + transactionType + ", amount="
				+ amount + ", effectiveDate=" + effectiveDate + "]";
	}
}