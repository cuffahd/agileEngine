package com.agile.engine.cuffaro.dto;

import java.io.Serializable;

import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.model.TransactionItem;

public class TransactionRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1582514313584568885L;
	
	private TransactionTypeEnum type;
	private Double amount;
	
	public TransactionRequestDTO() {
		//Default constructor without parameters
	}
	
	public TransactionRequestDTO(TransactionTypeEnum type, Double amount) {
		this.type = type;
		this.amount = amount;
	}
	
	public TransactionTypeEnum getType() {
		return type;
	}
	public void setType(TransactionTypeEnum type) {
		this.type = type;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	public TransactionItem toTransaction() {
		return new TransactionItem(this.type, this.amount);
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
		if (type != other.type) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TransactionRequestDTO [type=" + type + ", amount=" + amount + "]";
	}
}
