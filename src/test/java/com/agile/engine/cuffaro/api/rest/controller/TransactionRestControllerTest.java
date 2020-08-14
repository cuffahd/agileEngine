package com.agile.engine.cuffaro.api.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agile.engine.cuffaro.CuffaroApplication;
import com.agile.engine.cuffaro.dto.TransactionDTO;
import com.agile.engine.cuffaro.dto.TransactionRequestDTO;
import com.agile.engine.cuffaro.enums.TransactionTypeEnum;
import com.agile.engine.cuffaro.exceptions.InvalidArgumentException;
import com.agile.engine.cuffaro.exceptions.InvalidOperationException;
import com.agile.engine.cuffaro.exceptions.TransactionNotFoundException;
import com.agile.engine.cuffaro.service.ITransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CuffaroApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:db-test.properties")
public class TransactionRestControllerTest {
	
	@InjectMocks
    TransactionRestController restController;
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private ITransactionService transactionService;

    @Autowired
    ObjectMapper objectmapper;
    
    TransactionDTO transDto1;
    TransactionDTO transDto2;
    
    @Before
    public void setup() {
    	transDto1 = new TransactionDTO("4028808f73ea5a160173ea5a53fd0001", TransactionTypeEnum.credit, Double.valueOf(10), new Date(1500000));
    	transDto2 = new TransactionDTO("4028808f73ea5a160173ea5a53fd0002", TransactionTypeEnum.debit, Double.valueOf(11), new Date(15000000));
    }
    
    @Test
    public void test_get_history_returns_empty() throws Exception {    	
    	Mockito.when(transactionService.getTransactionHistory()).thenReturn(new ArrayList<TransactionDTO>());
    	String response = mvc.perform(get("/api/transactions"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
 
        Assert.assertEquals("[]", response);
    }
    
    @Test
    public void test_get_history_returns_one_item() throws Exception {    	 
    	List <TransactionDTO> transDTOList = new ArrayList<>();
    	transDTOList.add(transDto1);
    	
    	Mockito.when(transactionService.getTransactionHistory()).thenReturn(transDTOList);
    	String response = mvc.perform(get("/api/transactions"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
 
        Assert.assertEquals("[{\"transactionId\":\"4028808f73ea5a160173ea5a53fd0001\",\"transactionType\":\"credit\",\"amount\":10.0,"
        						+ "\"effectiveDate\":\"1970-01-01T00:25:00.000+00:00\"}]",
        		response);
    }
 
    @Test
    public void test_get_history_returns_more_than_one_item() throws Exception {    	
    	List <TransactionDTO> transDTOList = new ArrayList<>();
    	transDTOList.add(transDto1);
    	transDTOList.add(transDto2);
    	Mockito.when(transactionService.getTransactionHistory()).thenReturn(transDTOList);
    	String response = mvc.perform(get("/api/transactions"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
 
        Assert.assertEquals("[{\"transactionId\":\"4028808f73ea5a160173ea5a53fd0001\",\"transactionType\":\"credit\",\"amount\":10.0,"
        					+ "\"effectiveDate\":\"1970-01-01T00:25:00.000+00:00\"},"
        					+ "{\"transactionId\":\"4028808f73ea5a160173ea5a53fd0002\",\"transactionType\":\"debit\",\"amount\":11.0,"
        					+ "\"effectiveDate\":\"1970-01-01T04:10:00.000+00:00\"}]", 
        					response);
    }
 
    @Test
    public void test_find_transaction() throws Exception {
    	Mockito.when(transactionService.getTransaction(Mockito.anyString())).thenReturn(transDto1);
    	String response = mvc.perform(get("/api/transactions?transactionId=4028808f73ea5a160173ea5a53fd0001"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
 
        Assert.assertEquals("{\"transactionId\":\"4028808f73ea5a160173ea5a53fd0001\",\"transactionType\":\"credit\",\"amount\":10.0,"
        		+ "\"effectiveDate\":\"1970-01-01T00:25:00.000+00:00\"}", response);
    }
    
    @Test
    public void test_find_transaction_bad_request() throws Exception {
    	Mockito.when(transactionService.getTransaction(Mockito.anyString())).thenThrow(new InvalidArgumentException());
    	String response = mvc.perform(get("/api/transactions?transactionId=4028808f73ea5a160173ea5a53fd0001"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andReturn().getResponse().getContentAsString();
 
    	Assert.assertEquals("invalid ID supplied", response);
    }
    
    @Test
    public void test_find_transaction_transaction_not_found() throws Exception {
    	Mockito.when(transactionService.getTransaction(Mockito.anyString())).thenThrow(new TransactionNotFoundException());
    	String response = mvc.perform(get("/api/transactions?transactionId=4028808f73ea5a160173ea5a53fd0001"))
                .andExpect(status().is(HttpStatus.GONE.value()))
                .andReturn().getResponse().getContentAsString();
 
    	Assert.assertEquals("transaction not found", response);
    }
 
    @Test
    public void test_create_transaction() throws Exception {
    	TransactionRequestDTO dto = new TransactionRequestDTO(TransactionTypeEnum.credit, Double.valueOf(10));
    	Mockito.when(transactionService.createTransaction(dto)).thenReturn(transDto1);
    	String response = mvc.perform(post("/api/transactions")
    			 .content(objectmapper.writeValueAsString(dto))
    			    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn().getResponse().getContentAsString();
 
    	Assert.assertEquals("transaction stored", response);
    }
    
    @Test
    public void test_create_transaction_refused() throws Exception {
    	TransactionRequestDTO dto = new TransactionRequestDTO(TransactionTypeEnum.credit, Double.valueOf(10));
    	Mockito.when(transactionService.createTransaction(dto)).thenThrow(new InvalidOperationException());
    	String response = mvc.perform(post("/api/transactions")
    			 .content(objectmapper.writeValueAsString(dto))
    			    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andReturn().getResponse().getContentAsString();
 
    	Assert.assertEquals("invalid input", response);
    }

    
}
