package com.agile.engine.cuffaro.dto;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class AccountBalanceDTOTest {

	@Test
	public void test_hashCode() {
		AccountBalanceDTO acBalance1 = new AccountBalanceDTO();
		Assert.assertEquals(961, acBalance1.hashCode());
		acBalance1.setAmount(Double.valueOf(1000));
		acBalance1.setEffectiveDate(new Date(150000));
		Assert.assertEquals(-782561871, acBalance1.hashCode());
	}
	
	@Test
	public void test_equals() {
		AccountBalanceDTO acBalance1 = new AccountBalanceDTO();
		AccountBalanceDTO acBalance2 = new AccountBalanceDTO();
		
		Assert.assertEquals(acBalance1, acBalance1);
		Assert.assertNotEquals(acBalance1,null);
		Assert.assertEquals(acBalance2, acBalance1);
		Assert.assertNotEquals(acBalance1, 1L);
		Assert.assertEquals(acBalance1, acBalance2);
		Assert.assertEquals(acBalance2, acBalance1);
		acBalance1.setAmount(Double.valueOf(10));
		Assert.assertNotEquals(acBalance2, acBalance1);
		Assert.assertNotEquals(acBalance1, acBalance2);
		acBalance2.setAmount(Double.valueOf(10));
		Assert.assertEquals(acBalance2, acBalance1);
		Assert.assertEquals(acBalance1, acBalance2);
		acBalance1.setEffectiveDate(new Date(150000));
		Assert.assertNotEquals(acBalance2, acBalance1);
		Assert.assertNotEquals(acBalance1, acBalance2);
		acBalance2.setEffectiveDate(new Date(150000));
		Assert.assertEquals(acBalance2, acBalance1);
		Assert.assertEquals(acBalance1, acBalance2);
	}
}
