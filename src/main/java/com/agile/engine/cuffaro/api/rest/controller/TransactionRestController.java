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

/**
 * Controller that manages transactions.
 * @author hcuff
 *
 */
@RestController
public class TransactionRestController {

	@Autowired
	private ITransactionService transactionService;
	
	/**
	 * Retrieves a list with all the transactions stored.
	 * @return ResponseEntity<List<TransactionDTO>> - represents a list with all the transactions.
	 */
	@RequestMapping(value="/api/transactions", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionDTO>> getTransactionHistory(){
		return ResponseEntity.ok(transactionService.getTransactionHistory());
	}
	
	/**
	 * Retrieves the transaction identified with the param provided.
	 * @param transactionId
	 * @return Transaction information for the given transactionId
	 */
	@RequestMapping(method = RequestMethod.GET, value="/api/transactions",  params = "transactionId")
	public  ResponseEntity<Object> findTransaction(@RequestParam("transactionId") String transactionId){
		
		try {
			TransactionDTO dto = transactionService.getTransaction(transactionId);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (InvalidArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid ID supplied");
		}catch(TransactionNotFoundException  e) {
			return ResponseEntity.status(HttpStatus.GONE).body("transaction not found");
		}
		

	}
	
	/**
	 * Creates new transactions.
	 * @param transactionRequest
	 * @return 
	 */
	@RequestMapping(method = RequestMethod.POST, value ="/api/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createTransaction(@RequestBody TransactionRequestDTO transactionRequest){
		try {
			transactionService.createTransaction(transactionRequest);
			return new ResponseEntity<>("transaction stored", HttpStatus.CREATED);
		} catch (InvalidOperationException e) {
			return ResponseEntity.status(422).body("invalid input");
		}
	}
	
	
}
