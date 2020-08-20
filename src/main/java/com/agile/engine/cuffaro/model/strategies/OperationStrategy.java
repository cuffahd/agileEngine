package com.agile.engine.cuffaro.model.strategies;

import java.math.BigDecimal;

/**
 * Methods that should implement the transactions.
 * 
 * @author hcuff
 *
 */

public interface OperationStrategy {
	/**
	 * Defines the way that the transaction modifies the balance amount.
	 * @param otherValue
	 * @return
	 */
	public BigDecimal doOperation(BigDecimal otherValue);
}
