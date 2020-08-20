package com.agile.engine.cuffaro.dto.operations;

import java.math.BigDecimal;
import java.util.Date;

import com.agile.engine.cuffaro.dto.TransactionDTO;
import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.model.strategies.OperationStrategy;

public class TransactionDebitDTO extends TransactionDTO implements OperationStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869384607697410976L;

	public TransactionDebitDTO(String transactionId, BigDecimal amount, Date effectiveDate) {
		super(transactionId, amount, effectiveDate);
	}

	public TransactionDebitDTO() {}

	public TransactionDebitDTO(BigDecimal amount) {
		super(null, amount, null);
	}

	@Override
	public synchronized BigDecimal doOperation(BigDecimal otherValue) {
		return otherValue.subtract(this.getAmount());
	}

	@Override
	public TransactionTypeEnum getTransactionType() {
		return TransactionTypeEnum.debit;
	} 

}
