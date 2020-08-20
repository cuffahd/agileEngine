package com.agile.engine.cuffaro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.engine.cuffaro.dao.IAccountBalanceDAO;
import com.agile.engine.cuffaro.dto.AccountBalanceDTO;
import com.agile.engine.cuffaro.model.AccountBalance;

/**
 * Account Balance Service
 * @author hcuff
 *
 */
@Service
public class AccountBalanceService implements IAccountBalanceService{

	@Autowired
	public AccountBalanceService(IAccountBalanceDAO accountBalanceDAO) {
		this.accountBalanceDAO = accountBalanceDAO;
	}
	
	private final IAccountBalanceDAO accountBalanceDAO;
	
	/**
	 * Retrieves the account balance.
	 * If there is no account balance return empty dto.
	 */
	@Override
	@Transactional(readOnly = true)
	public AccountBalanceDTO getAccountBalance() {
		Optional<AccountBalance> accountOptional = accountBalanceDAO.findById(1L);
		//TODO: If we have more than 1 accounts remove this hardcoded 1, and add the ID as method parameter
		if(accountOptional.isPresent()) {
			return new AccountBalanceDTO(accountOptional.get());
		}else {
			return new AccountBalanceDTO();
		}
		
	}

	
}
