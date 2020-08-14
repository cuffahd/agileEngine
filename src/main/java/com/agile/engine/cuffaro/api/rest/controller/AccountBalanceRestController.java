package com.agile.engine.cuffaro.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agile.engine.cuffaro.dto.AccountBalanceDTO;
import com.agile.engine.cuffaro.service.IAccountBalanceService;

@RestController
public class AccountBalanceRestController {

	@Autowired
	private IAccountBalanceService accountBalanceService;
	
	@RequestMapping(value="/api/default", method = RequestMethod.GET)
	public ResponseEntity<AccountBalanceDTO> getTransactionHistory(){
		return ResponseEntity.ok(accountBalanceService.getAccountBalance());
	}
}
