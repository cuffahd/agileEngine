package com.agile.engine.cuffaro.dto;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.model.TransactionItem;

public class TransactionDTOTest {

	@Test
	public void test_hashCode() {
		TransactionDTO transactionDto = new TransactionDTO(new TransactionItem());
		Assert.assertEquals(923521, transactionDto.hashCode());
		transactionDto.setAmount(Double.valueOf(1000));
		transactionDto.setEffectiveDate(new Date(150000));
		transactionDto.setTransactionId("12345567890");
		transactionDto.setTransactionType(TransactionTypeEnum.credit);
		Assert.assertEquals(1312839815, transactionDto.hashCode());
	}
	
	@Test
	public void test_equals() {
		TransactionDTO transactionDTO1 = new TransactionDTO(new TransactionItem());
		TransactionDTO transactionDTO2 = new TransactionDTO(new TransactionItem());
		
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
		transactionDTO1.setTransactionType(TransactionTypeEnum.credit);
		Assert.assertNotEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, transactionDTO2);
		transactionDTO2.setTransactionType(TransactionTypeEnum.credit);
		Assert.assertEquals(transactionDTO2, transactionDTO1);
		Assert.assertEquals(transactionDTO1, transactionDTO2);
		transactionDTO1.setAmount(Double.valueOf(10));
		Assert.assertNotEquals(transactionDTO2, transactionDTO1);
		Assert.assertNotEquals(transactionDTO1, transactionDTO2);
		transactionDTO2.setAmount(Double.valueOf(10));
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
