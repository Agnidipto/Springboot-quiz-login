package com.cg.loginapp.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author --> Sai Vineeth Neeli 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.loginapp.model.LoginDTO;
import com.cg.loginapp.model.UserDTO;
import com.cg.loginapp.service.UserServices;
/**
 * @author Sai Veenith Neeli 
 */


@RestController
public class UserContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(UserContoller.class);
	
	@Autowired
	private UserServices service; 
	
	
	/*
	 * User can SignUp 
	 */
	@PostMapping(value="/SignUp")
	public ResponseEntity<String> signUpPage(@RequestBody UserDTO userDto) throws SignUpExceptions
	{
		logger.info("Sign up in user controller is accessed");
		service.addSignUpDetails(userDto);
		return new ResponseEntity<>("Added Successfully",HttpStatus.ACCEPTED);
	}
	
	/*
	 * User Login page
	 */
	@PostMapping(value="/Login")
	public ResponseEntity<String> loginPage(@RequestBody LoginDTO loginDto) throws SignUpExceptions,NullPointerException
	{
		logger.info("Login in user controller is accessed");
        String s = service.login(loginDto.getEmailId(),loginDto.getPassword(),loginDto.getUserType());
         return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	
	/*
	 * If User has Forgot the Password
	 */
	@PutMapping(value="/User/forgotPassword/{securityAns}")
	public ResponseEntity<String> forgotPassWord(@PathVariable String securityAns,@RequestBody LoginDTO loginDto) throws SignUpExceptions,NullPointerException
	{
		logger.info("Forgot password in user controller is accessed");
		String s = service.forgotPassword(loginDto.getEmailId(), loginDto.getUserType(), securityAns, loginDto.getPassword(),loginDto.getPassword());
		return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	
	
	 
}
