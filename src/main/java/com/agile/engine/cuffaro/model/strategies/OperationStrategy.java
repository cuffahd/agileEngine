package com.agile.engine.cuffaro.model.strategies;

import java.math.BigDecimal;

public interface OperationStrategy {
	public BigDecimal doOperation(BigDecimal otherValue);
}
