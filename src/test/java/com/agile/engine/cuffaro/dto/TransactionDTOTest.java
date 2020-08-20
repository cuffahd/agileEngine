package com.agile.engine.cuffaro.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.agile.engine.cuffaro.dto.operations.TransactionDebitDTO;

public class TransactionDTOTest {

	@Test
	public void test_equals() {
		TransactionDTO transactionDTO1 = new TransactionDebitDTO();
		TransactionDTO transactionDTO2 = new TransactionDebitDTO();
		
		Assert.assertEquals(transactionDTO1, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1,null);
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, 1L);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		transactionDTO1.setTransactionId("1234567890");
		Assert.assertNotEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, transactionDTO2);
		transactionDTO2.setTransactionId("1234567890");
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
		transactionDTO1.setAmount(new BigDecimal(10));
		Assert.assertNotEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, transactionDTO2);
		transactionDTO2.setAmount(new BigDecimal(10));
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
		transactionDTO1.setEffectiveDate(new Date(150000));
		Assert.assertNotEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, transactionDTO2);
		transactionDTO2.setEffectiveDate(new Date(150000));
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
	}
}
