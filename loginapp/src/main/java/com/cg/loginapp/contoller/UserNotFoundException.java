package com.cg.loginapp.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Neelambari k
 */
public class UserNotFoundException extends Exception{

	private static final Logger logger = LoggerFactory.getLogger(UserNotFoundException.class);
	
	public UserNotFoundException(String msg) {
		super(msg);
		logger.info(" UserNotFoundException is invoked");
		
	}

}
