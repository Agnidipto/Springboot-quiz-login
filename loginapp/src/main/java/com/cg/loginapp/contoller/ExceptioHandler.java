package com.cg.loginapp.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author --> Sai Vineeth Neeli 
 */


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author Sai Veenith Neeli 
 */


@RestControllerAdvice
public class ExceptioHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptioHandler.class);
	
	/*
	 * Exception Handler for SignUpExceptions
	 */
	@ExceptionHandler(SignUpExceptions.class)
	public ResponseEntity<String> signUpExceptions(SignUpExceptions s)
	{
		logger.info("Exception handler for SignUpExceptions is invoked");
		return new ResponseEntity<>(s.getMessage(),HttpStatus.BAD_REQUEST);
	}
    
	/*
	 * Exception Handler for UserNotFoundException
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException s)
	{
		logger.info("Exception handler for UserNotFoundException is invoked");
		return new ResponseEntity<>(s.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
 