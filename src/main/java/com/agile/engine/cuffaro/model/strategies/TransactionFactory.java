package com.agile.engine.cuffaro.model.strategies;

import java.math.BigDecimal;

import com.agile.engine.cuffaro.dto.TransactionDTO;
import com.agile.engine.cuffaro.dto.operations.TransactionCreditDTO;
import com.agile.engine.cuffaro.dto.operations.TransactionDebitDTO;
import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.exceptions.InvalidArgumentException;
import com.agile.engine.cuffaro.model.TransactionItem;
/**
 * Factory class that returns TransactionsDTO based on the transaction type.
 * See different transaction types in TransactionTypeEnum
 * @author hcuff
 *
 */
public final class TransactionFactory {

	private TransactionFactory() {}
	
	/**
	 * Create and populate the amount of a transactionDTO
	 * @param type
	 * @param amount
	 * @return
	 * @throws InvalidArgumentException
	 */
	public static TransactionDTO createTransactionDTO(TransactionTypeEnum type, BigDecimal amount) throws InvalidArgumentException {
		TransactionDTO dto = createTransactionDTO(type);
		dto.setAmount(amount);
		return dto;
	}
	
	/**
	 * Create and populate a transactionDTO based on a transactionItem
	 * @param item
	 * @return
	 * @throws InvalidArgumentException
	 */
	public static TransactionDTO createTransactionDTO(TransactionItem item) throws InvalidArgumentException {
		TransactionDTO dto = createTransactionDTO(item.getTransactionType());
		dto.setAmount(item.getAmount());
		dto.setEffectiveDate(item.getEffectiveDate());
		dto.setTransactionId(item.getTransactionId());
		return dto;
	}
	
	/**
	 * Creates a TransactionDTO based on a transactionTypeEnum.
	 * @param type
	 * @return
	 * @throws InvalidArgumentException
	 */
	public static TransactionDTO createTransactionDTO(TransactionTypeEnum type) throws InvalidArgumentException {
		switch (type) {
		case credit:
			return new TransactionCreditDTO();
		case debit:
			return new TransactionDebitDTO();
		default:
			break;
		}
		throw new InvalidArgumentException("Invalid Operation Type");
	}
}
