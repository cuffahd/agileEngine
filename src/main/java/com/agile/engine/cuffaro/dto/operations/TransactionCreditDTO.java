package com.agile.engine.cuffaro.dto.operations;

import java.math.BigDecimal;
import java.util.Date;

import com.agile.engine.cuffaro.dto.TransactionDTO;
import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.model.strategies.OperationStrategy;

public class TransactionCreditDTO  extends TransactionDTO implements OperationStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5150849666886997788L;

	public TransactionCreditDTO(String transactionId, BigDecimal amount, Date effectiveDate) {
		super(transactionId, amount, effectiveDate);
	}

	public TransactionCreditDTO() {	}

	public TransactionCreditDTO(BigDecimal amount) {
		super(null, amount, null);
	}

	@Override
	public BigDecimal doOperation(BigDecimal otherValue) {
		return this.getAmount().add(otherValue);
		
	}

	@Override
	public TransactionTypeEnum getTransactionType() {
		return TransactionTypeEnum.credit;
	} 

}
