package com.agile.engine.cuffaro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agile.engine.cuffaro.dao.IAccountBalanceDAO;
import com.agile.engine.cuffaro.dao.ITransactionDAO;
import com.agile.engine.cuffaro.dto.TransactionDTO;
import com.agile.engine.cuffaro.dto.TransactionRequestDTO;
import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.exceptions.InvalidArgumentException;
import com.agile.engine.cuffaro.exceptions.InvalidOperationException;
import com.agile.engine.cuffaro.exceptions.TransactionNotFoundException;
import com.agile.engine.cuffaro.model.AccountBalance;
import com.agile.engine.cuffaro.model.TransactionItem;

/**
 * Transaction Service
 * @author hcuff
 *
 */
@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionDAO transactionDAO;
	
	@Autowired
	private IAccountBalanceDAO accountBalanceDAO;
	
	/**
	 * Retrieves all the transaction history.
	 */
	@Override
	public List<TransactionDTO> getTransactionHistory() {
		List<TransactionItem> items = transactionDAO.findAll();
		List<TransactionDTO> transactionDTOList = new ArrayList<>();
		for (TransactionItem item : items) {
			transactionDTOList.add(new TransactionDTO(item));
		}
		return transactionDTOList;
	}

	/**
	 * Retrieves a specific transaction.
	 */
	@Override
	public TransactionDTO getTransaction(String transactionId) throws InvalidArgumentException, TransactionNotFoundException {
		validateUUID(transactionId);
		
		Optional<TransactionItem> item = transactionDAO.findById(transactionId);
		if(item.isPresent()) {
			return new TransactionDTO(item.get());
		} else {
			throw new TransactionNotFoundException();
		}
	}

	private void validateUUID(String transactionId) throws InvalidArgumentException {
		//TODO: Improve UUID validation
		if(transactionId.length()!=32) {
			throw new InvalidArgumentException();			
		}		
	}

	/**
	 * Creates a new transaction and updates the account balance.
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public TransactionDTO createTransaction(TransactionRequestDTO transactionRequestDTO) throws InvalidOperationException {
		AccountBalance balance;
		Optional<AccountBalance> optionalBalance = accountBalanceDAO.findById(1L);
		//TODO: if we manage more than 1 account, please remove the hardcoded value.
		if(optionalBalance.isPresent()) {
			balance = optionalBalance.get();
		}else {
			balance = new AccountBalance(1L, Double.valueOf(0));
			
		}
		if(TransactionTypeEnum.debit.equals(transactionRequestDTO.getType())) {
			balance.reduceAmount(transactionRequestDTO.getAmount());
			if(balance.getAmount() < Double.valueOf(0)) {
				throw new InvalidOperationException();
				//TODO: Log exception
			}
		}else {
			balance.increaseAmount(transactionRequestDTO.getAmount());
		}
		TransactionItem item = transactionDAO.save(transactionRequestDTO.toTransaction());
		balance.setEffectiveDate(item.getEffectiveDate());
		accountBalanceDAO.save(balance);
		return new TransactionDTO(item);
	}

}
