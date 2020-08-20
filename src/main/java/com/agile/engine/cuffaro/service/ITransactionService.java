package com.agile.engine.cuffaro.service;

import java.util.List;

import com.agile.engine.cuffaro.dto.TransactionDTO;
import com.agile.engine.cuffaro.dto.TransactionRequestDTO;
import com.agile.engine.cuffaro.exceptions.InvalidArgumentException;
import com.agile.engine.cuffaro.exceptions.InvalidOperationException;
import com.agile.engine.cuffaro.exceptions.TransactionNotFoundException;

public interface ITransactionService {

	public List<TransactionDTO> getTransactionHistory() throws InvalidArgumentException;

	public TransactionDTO getTransaction(String transactionId) throws InvalidArgumentException, TransactionNotFoundException;
	
	public TransactionDTO createTransaction(TransactionRequestDTO transactionRequestDTO) throws InvalidOperationException, InvalidArgumentException;
}
