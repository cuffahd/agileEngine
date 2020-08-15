package com.agile.engine.cuffaro.exceptions;

import java.io.Serializable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class InvalidArgumentException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4023294298416521143L;

	private static final Logger logger = LogManager.getLogger(InvalidArgumentException.class);
	
	private final Exception exception;
	private final String message;
	
	public InvalidArgumentException() {
		this.exception = new Exception();
		this.message= "";
	}
	
	public InvalidArgumentException(String message) {
		this.exception = new Exception();
		this.message = message;
		logger.error(message);
	}
	
	public InvalidArgumentException(String message, Exception e) {
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
