package com.agile.engine.cuffaro.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agile.engine.cuffaro.dto.TransactionDTO;
import com.agile.engine.cuffaro.dto.TransactionRequestDTO;
import com.agile.engine.cuffaro.exceptions.InvalidArgumentException;
import com.agile.engine.cuffaro.exceptions.InvalidOperationException;
import com.agile.engine.cuffaro.exceptions.TransactionNotFoundException;
import com.agile.engine.cuffaro.service.ITransactionService;

@RestController
public class TransactionRestController {

	@Autowired
	private ITransactionService transactionService;
	
	@RequestMapping(value="/api/transactions", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionDTO>> getTransactionHistory(){
		return ResponseEntity.ok(transactionService.getTransactionHistory());
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/api/transactions",  params = "transactionId")
	public  ResponseEntity<String> findTransaction(@RequestParam("transactionId") String transactionId){
		
		try {
			TransactionDTO dto = transactionService.getTransaction(transactionId);
			return ResponseEntity.ok(dto.toJson());
		} catch (InvalidArgumentException e) {
			return ResponseEntity.status(HttpStatus.GONE).body("invalid ID supplied");
		}catch(TransactionNotFoundException  e) {
			return ResponseEntity.status(HttpStatus.GONE).body("transaction not found");
		}
		

	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/api/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTransaction(@RequestBody TransactionRequestDTO transactionRequest){
		try {
			transactionService.createTransaction(transactionRequest);
			return ResponseEntity.ok("transaction stored");
		} catch (InvalidOperationException e) {
			return ResponseEntity.status(422).body("invalid input");
		}
	}
	
	
}
