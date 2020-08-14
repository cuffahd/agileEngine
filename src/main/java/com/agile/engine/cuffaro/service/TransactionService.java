package com.agile.engine.cuffaro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionDAO transactionDAO;
	
	@Autowired
	private IAccountBalanceDAO accountBalanceDAO;
	
	@Override
	public List<TransactionDTO> getTransactionHistory() {
		List<TransactionItem> items = transactionDAO.findAll();
		List<TransactionDTO> transactionDTOList = new ArrayList<>();
		for (TransactionItem item : items) {
			transactionDTOList.add(new TransactionDTO(item));
		}
		return transactionDTOList;
	}

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

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public TransactionDTO createTransaction(TransactionRequestDTO transactionRequestDTO) throws InvalidOperationException {
		AccountBalance balance;
		Optional<AccountBalance> optionalBalance = accountBalanceDAO.findById(1L);
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
