package com.cg.loginapp.service;
/**
 * @author Neelambari k 
 */

import java.util.List; 
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.loginapp.contoller.SignUpExceptions;
import com.cg.loginapp.contoller.UserNotFoundException;
import com.cg.loginapp.entity.User;
import com.cg.loginapp.model.UserDTO;
import com.cg.loginapp.repository.LoginRepository;


@Service
public class AdminServices {
	 
	private static final Logger logger = LoggerFactory.getLogger(AdminServices.class);
	 
	@Autowired
	LoginRepository repo;
	
	@Autowired
	UserServices userServices; 
	
	/*
	 *  Add user service
	 */
	public  String addUser(UserDTO userdto) throws SignUpExceptions
	{
		logger.info("Add user service in Admin service is accessed");
		userServices.addSignUpDetails(userdto);
		return "Added successfully";
	} 
	
	/*
	 *  Update user service
	 */
	public String updateUser(String emailId,String userType,UserDTO userdto) throws UserNotFoundException,SignUpExceptions
	{
		logger.info("Update user service in Admin service is accessed");
		try {
      Optional<User> optional= Optional.of(repo.findByCid(emailId,userType));
		
		if(optional.isPresent()) {
			
			if(userServices.validation(userdto)) {	
			User user= optional.get();
			user.setDob(userdto.getDob());
            user.setPassword(userdto.getPassword());
   	        user.setFirstName(userdto.getFirstName());
		    user.setLastName(userdto.getLastName());
	        user.setPhoneNo(userdto.getPhoneNo());
 	        user.setSecurityAns(userdto.getSecurityAns());
			repo.saveAndFlush(user);}
			
		}
		}
		catch(NullPointerException e){
			throw new UserNotFoundException("User not found for the given id,Update process failed");
		}
		
		return "Updated successsfully";
	}
	
	/*
	 *  List all user service
	 */
	public List<User> listAllUsers(){
		logger.info("List all user service in Admin service is accessed");
		 return repo.findAll();
	} 
	 
	/*
	 *  List user service
	 */
	public User listUser(String emailId,String userType) throws UserNotFoundException
	{
		logger.info("List user service in Admin service is accessed");
	  User user = new User();
		try {
       Optional<User> optional= Optional.of(repo.findByCid(emailId,userType));
		
		if(optional.isPresent()) {
			user= optional.get();
		}
		}
		catch(NullPointerException e) {
			throw new UserNotFoundException("User not found for the given id,List process failed");
		}
		
		return user;
		
	}
	
	/*
	 *  Delete user service
	 */
	public String deleteUser(String emailId,String userType)throws UserNotFoundException {
		logger.info("Delete user service in Admin service is accessed");
		try {
		Optional<User> optional = Optional.of(repo.findByCid(emailId,userType));  
		User user;
		if(optional.isPresent()) {
			  user= optional.get();
			  repo.delete(user);
		} 
		 
		}
		catch(NullPointerException e){
			throw new UserNotFoundException("User not available ,Delete operation failed");
			
		}
			
		return "Deleted successfully"; 
	}
	
	
	
}
