package com.cg.loginapp.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sai Veenith Neeli 
 */


public class SignUpExceptions extends Throwable{
   
	private static final Logger logger = LoggerFactory.getLogger(SignUpExceptions.class);
	
	public SignUpExceptions(String msg)
	{
		super(msg);
		logger.info(" SignUpExceptions is invoked");
	}
}
