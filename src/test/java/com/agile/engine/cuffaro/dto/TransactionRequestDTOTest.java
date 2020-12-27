package com.agile.engine.cuffaro.dto;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.agile.engine.cuffaro.enums.TransactionTypeEnum;

public class TransactionRequestDTOTest {
	
	@Test
	public void test_equals() {
		TransactionRequestDTO transactionDTO1 = new TransactionRequestDTO();
		TransactionRequestDTO transactionDTO2 = new TransactionRequestDTO();
		
		Assert.assertEquals(transactionDTO1, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1,null);
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, 1L);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		transactionDTO1.setTransactionType(TransactionTypeEnum.credit);
		Assert.assertNotEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, transactionDTO2);
		transactionDTO2.setTransactionType(TransactionTypeEnum.credit);
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
		transactionDTO1.setAmount(new BigDecimal(10));
		Assert.assertNotEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, transactionDTO2);
		transactionDTO2.setAmount(new BigDecimal(10));
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
	}
}
