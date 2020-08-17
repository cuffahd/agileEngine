package com.agile.engine.cuffaro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

public class TransactionServiceTest {

	@Mock
	private ITransactionDAO transactionDAO;
	
	@Mock
	private IAccountBalanceDAO accountBalanceDAO;
	
	@InjectMocks
	TransactionService transactionService;
	
	TransactionItem item;
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	    item = new TransactionItem();
		item.setAmount(Double.valueOf(10));
		item.setEffectiveDate(new Date(1500000));
		item.setTransactionId("4028ab3e73ede71e0173ede743680001");
		item.setTransactionType(TransactionTypeEnum.credit);
	}
		
	@Test
	public void test_getTransactionHistory_emptyList() {
		Mockito.when(transactionDAO.findAll()).thenReturn(new ArrayList<TransactionItem>());
		
		List<TransactionDTO> listReturned = transactionService.getTransactionHistory();
		Assert.assertTrue(listReturned.isEmpty());
	}
	
	@Test
	public void test_getTransactionHistory_returnOneItem() {
		List<TransactionItem> transactionItemList = new ArrayList<TransactionItem>();
		transactionItemList.add(item);
		
		Mockito.when(transactionDAO.findAll()).thenReturn(transactionItemList);
		
		List<TransactionDTO> listReturned = transactionService.getTransactionHistory();
		Assert.assertFalse(listReturned.isEmpty());
		Assert.assertEquals(transactionItemList.size(), listReturned.size());
		Assert.assertEquals(new TransactionDTO(item), listReturned.get(0));

	}
	
	@Test(expected = InvalidArgumentException.class)
	public void test_getTransaction_invalidUUID() throws InvalidArgumentException, TransactionNotFoundException {
		transactionService.getTransaction("INVALID TRANSACTION");
		Assert.fail();
	}
	
	@Test(expected = TransactionNotFoundException.class)
	public void test_getTransaction_throwsTransactionNotFoundException() throws InvalidArgumentException, TransactionNotFoundException {
		Mockito.when(transactionDAO.findById(Mockito.anyString())).thenReturn(Optional.empty());
		transactionService.getTransaction("TRANSACTION NOT FOUND --32 chars");
		Assert.fail();
	}
	
	@Test
	public void test_getTransaction() throws InvalidArgumentException, TransactionNotFoundException {
		Mockito.when(transactionDAO.findById(Mockito.anyString())).thenReturn(Optional.of(item));
		TransactionDTO transactionDTO = transactionService.getTransaction("4028ab3e73ede71e0173ede743680001");
		Assert.assertEquals(item.getTransactionId(), transactionDTO.getTransactionId());
		Assert.assertEquals(item.getAmount(), transactionDTO.getAmount());
		Assert.assertEquals(item.getEffectiveDate(), transactionDTO.getEffectiveDate());
		Assert.assertEquals(item.getTransactionType(), transactionDTO.getTransactionType());
	}	
	
	@Test(expected = InvalidOperationException.class)
	public void test_createTransaction_throwsInvalidOperationException() throws InvalidOperationException{
		Mockito.when(transactionDAO.findById(Mockito.anyString())).thenReturn(Optional.empty());
		
		TransactionRequestDTO dto = new TransactionRequestDTO();
		dto.setAmount(Double.valueOf(10));
		dto.setType(TransactionTypeEnum.debit);
				
		transactionService.createTransaction(dto);
		Assert.fail();
	}
	
	@Test
	public void test_createTransaction() throws InvalidOperationException{
		AccountBalance balance = new AccountBalance();
		balance.setAccountId(1L);
		balance.setAmount(Double.valueOf(100));
		balance.setEffectiveDate(new Date(170));
		
		TransactionRequestDTO dto = new TransactionRequestDTO();
		dto.setAmount(Double.valueOf(10));
		dto.setType(TransactionTypeEnum.debit);

		TransactionItem item = new TransactionItem();
		Mockito.when(accountBalanceDAO.findById(Mockito.anyLong())).thenReturn(Optional.of(balance));
		Mockito.when(transactionDAO.save(dto.toTransaction())).thenReturn(item);
		Mockito.when(accountBalanceDAO.save(balance)).thenReturn(balance);
				
		TransactionDTO returnedDTO = transactionService.createTransaction(dto);
		Assert.assertEquals(item.getTransactionId(), returnedDTO.getTransactionId());
		Assert.assertEquals(item.getAmount(), returnedDTO.getAmount());
		Assert.assertEquals(item.getEffectiveDate(), returnedDTO.getEffectiveDate());
		Assert.assertEquals(item.getTransactionType(), returnedDTO.getTransactionType());
		
	}
}
