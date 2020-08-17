package com.agile.engine.cuffaro.service;


import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.agile.engine.cuffaro.dao.IAccountBalanceDAO;
import com.agile.engine.cuffaro.dto.AccountBalanceDTO;
import com.agile.engine.cuffaro.model.AccountBalance;


public class AccountBalanceServiceTest {

	@Mock
    IAccountBalanceDAO accountBalanceDAO;
	    	
	@InjectMocks
	AccountBalanceService accountBalanceService;
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_getAccountBalance_presentBalance() {
		AccountBalanceDTO balanceDTO = new AccountBalanceDTO();
		balanceDTO.setAmount(Double.valueOf(10));
		balanceDTO.setEffectiveDate(new Date(15000000));
		
		AccountBalance balance = new AccountBalance();
		balance.setAccountId(1L);
		balance.setAmount(Double.valueOf(10));
		balance.setEffectiveDate(new Date(15000000));
		Mockito.when(accountBalanceDAO.findById(Mockito.anyLong())).thenReturn(Optional.of(balance));
		
		AccountBalanceDTO dtoReturned = accountBalanceService.getAccountBalance();
		Assert.assertEquals(balanceDTO, dtoReturned);
	}
	
	@Test
	public void test_getAccountBalance_balanceNotFound() {
		AccountBalanceDTO balanceDTO = new AccountBalanceDTO();
		balanceDTO.setAmount(Double.valueOf(10));
		balanceDTO.setEffectiveDate(new Date(15000000));
		
		Mockito.when(accountBalanceDAO.findById(Mockito.anyLong())).thenReturn(Optional.of(new AccountBalance()));
		
		AccountBalanceDTO dtoReturned = accountBalanceService.getAccountBalance();
		Assert.assertNotEquals(balanceDTO, dtoReturned);
		Assert.assertNull(dtoReturned.getAmount());
		Assert.assertNull(dtoReturned.getEffectiveDate());
	}
}
