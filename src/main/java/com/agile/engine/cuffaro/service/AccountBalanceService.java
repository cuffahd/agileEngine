package com.agile.engine.cuffaro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.engine.cuffaro.dao.IAccountBalanceDAO;
import com.agile.engine.cuffaro.dto.AccountBalanceDTO;
import com.agile.engine.cuffaro.model.AccountBalance;

@Service
public class AccountBalanceService implements IAccountBalanceService{

	@Autowired
	private IAccountBalanceDAO accountBalanceDAO;
	
	@Override
	public AccountBalanceDTO getAccountBalance() {
		Optional<AccountBalance> accountOptional = accountBalanceDAO.findById(1L);
		if(accountOptional.isPresent()) {
			return new AccountBalanceDTO(accountOptional.get());
		}else {
			return new AccountBalanceDTO();
		}
		
	}

	
}
