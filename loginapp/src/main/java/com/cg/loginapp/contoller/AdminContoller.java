package com.cg.loginapp.contoller;

/**
 * @author Sai Vineeth Neeli  
 */


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.loginapp.entity.User;
import com.cg.loginapp.model.UserDTO;
import com.cg.loginapp.service.AdminServices;
import com.cg.loginapp.service.UserServices;

@RestController
public class AdminContoller {
	 private static final Logger logger = LoggerFactory.getLogger(AdminContoller.class);
	 
	@Autowired
	 AdminServices adminService;
    
	@Autowired
	 UserServices service; 

	/*
	 *  Admin login page
	 */
	@PostMapping(value="/admin/{emailId}/{password}/")
	public ResponseEntity<String> loginPage(@PathVariable String emailId , @PathVariable String password) throws SignUpExceptions,NullPointerException
	{
		logger.info("Login in user controller is accessed");
        String s = service.adminlogin(emailId,password);
         return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	
	/*
	 *  Admin can add the User
	 */
	@PostMapping(value="/admin/addUser") 
	public ResponseEntity<String> adminAddUser(@RequestBody UserDTO userdto) throws SignUpExceptions
	{
		logger.info("Add user in admin controller is accessed");
		String s = adminService.addUser(userdto);
		return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
		
	/*
	 *  Admin can update the User Details by EmailId and UserType
	 */
	
	@PutMapping(value="/admin/updateUsers/{emailId}/{userType}") 
	public ResponseEntity<String> adminUpdateUser(@PathVariable String emailId ,@PathVariable String userType ,@RequestBody UserDTO userdto) throws UserNotFoundException,SignUpExceptions
	{ 
		logger.info("Update user in admin controller is accessed");
		String s = adminService.updateUser(emailId, userType, userdto);
		return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	 
	/*
	 *  Admin can view all the Users
	 */
	@GetMapping(value="/admin/getAllUser") 
	public List<User> adminGetAllUser()
	{
		logger.info("Get all user in admin controller is accessed");
		return adminService.listAllUsers();
	}
	
	/*
	 *  Admin can view all the User Details by EmailId and UserType
	 */
	@GetMapping(value="/admin/getById/{emailId}/{userType}")
	public User adminGetUserById(@PathVariable String emailId , @PathVariable String userType) throws UserNotFoundException
	{
		logger.info("List user in admin controller is accessed");
		return adminService.listUser(emailId,userType);
	}
	
	/*
	 *  Admin can Delete the User
	 */
	@DeleteMapping(value="/admin/deleteById/{emailId}/{userType}") 
	public String adminDeleteUserById(@PathVariable String emailId , @PathVariable String userType) throws UserNotFoundException
	{
		logger.info("Delete user in admin controller is accessed");
		return adminService.deleteUser(emailId, userType);
	}
	

}
