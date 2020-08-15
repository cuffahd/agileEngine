package com.agile.engine.cuffaro.exceptions;

import java.io.Serializable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TransactionNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4023294298416521143L;

	private static final Logger logger = LogManager.getLogger(TransactionNotFoundException.class);
	
	private final Exception exception;
	private final String message;
	
	public TransactionNotFoundException() {
		this.exception = new Exception();
		this.message= "";
	}
	
	public TransactionNotFoundException(String message) {
		this.exception = new Exception();
		this.message = message;
		logger.error(message);
	}
	
	public TransactionNotFoundException(String message, Exception e) {
		this.message = message;
		this.exception = e;
		logger.error(message, e);
	}
	
	public Exception getException(){
		return this.exception;
	}
	
	public String getMessage() {
		return message;
	}
	
}
