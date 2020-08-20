package com.agile.engine.cuffaro.api.rest.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agile.engine.cuffaro.CuffaroApplication;
import com.agile.engine.cuffaro.dto.AccountBalanceDTO;
import com.agile.engine.cuffaro.service.IAccountBalanceService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CuffaroApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:db-test.properties")
public class AccountBalanceRestControllerTest {

	@InjectMocks
    AccountBalanceRestController restController;
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private IAccountBalanceService accountBalanceService;
    
    
    @Test
    public void test_return_empty_balance() throws Exception {    	
    	Mockito.when(accountBalanceService.getAccountBalance()).thenReturn(new AccountBalanceDTO());
    	String response = mvc.perform(get("/api/default"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
 
        Assert.assertEquals("{\"amount\":null,\"effectiveDate\":null}", response);
    }
    
    @Test
    public void test_return_not_empty_balance() throws Exception {
    	AccountBalanceDTO dtoToReturn = new AccountBalanceDTO();
    	dtoToReturn.setAmount(new BigDecimal(100));
    	dtoToReturn.setEffectiveDate(new Date(1500000));
    	
    	
    	Mockito.when(accountBalanceService.getAccountBalance()).thenReturn(dtoToReturn);
    	String response = mvc.perform(get("/api/default"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
 
        Assert.assertEquals("{\"amount\":100,\"effectiveDate\":\"1970-01-01T00:25:00.000+00:00\"}", response);
    }
}
