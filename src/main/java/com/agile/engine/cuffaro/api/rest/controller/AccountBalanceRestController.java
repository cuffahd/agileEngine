package com.agile.engine.cuffaro.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agile.engine.cuffaro.dto.AccountBalanceDTO;
import com.agile.engine.cuffaro.service.IAccountBalanceService;

/**
 * Account Balance Rest Controller.
 * Contains operations to manage account balance.
 * @author hcuff
 *
 */
@RestController
public class AccountBalanceRestController {

	@Autowired
	private IAccountBalanceService accountBalanceService;
	
	/**
	 * Method that search the Account Balance.
	 * As we only have one user we always retrieve the account balance with ID 1. 
	 * @return ResponseEntity<AccountBalanceDTO>
	 */
	@RequestMapping(value="/api/default", method = RequestMethod.GET)
	public ResponseEntity<AccountBalanceDTO> getAccountBalance(){
		return ResponseEntity.ok(accountBalanceService.getAccountBalance());
	}
}
