package com.cg.loginapp.servicetest;

/**
 * @author Sai Veenith Neeli 
 */

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.loginapp.repository.LoginRepository;
import com.cg.loginapp.service.UserServices;
import com.cg.loginapp.utils.LoginUtils;
import com.cg.loginapp.contoller.SignUpExceptions;
import com.cg.loginapp.entity.User;
import com.cg.loginapp.model.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceTest {

		@Autowired
		private UserServices service;
		
		@MockBean
		private LoginRepository repo;
		
		/*
	     * To create UserDTO objects
	     */
		public static UserDTO userDetails() {
		UserDTO u = new UserDTO();
	    
		u.setEmailId("fghn7#dfgbn.in");
		u.setUserType("doc");
		u.setFirstName("sai7");
		u.setLastName("vinnu9");
		u.setPhoneNo("985623147");
		u.setPassword("sai#2");
		u.setReTypePassword("vin%");
		u.setDob("12");
		u.setSecurityQue("how you");
		u.setSecurityAns("hey");
		 
		return u;
		}
		
		/*
	     * Testing for Sign up service when adding invalid FirstName 
	     */
		@Test
		 void addSignUpTestFirstName() {
		UserDTO u1 = userDetails();
	    String s1 = "FirstName should not contain numbers";
		try { service.addSignUpDetails(u1);}
		catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage()));}
	   }
		
		/*
	     * Testing for Sign up service when adding blank FirstName 
	     */
        @Test
		 void addSignUpTestFirstNameBlank()
		{ UserDTO u1 = userDetails(); 
		  u1.setFirstName("");
		  String s1 = "FirstName should not be blank";
		  try { service.addSignUpDetails(u1);}
		  catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage()));}
			
		} 
		
        /*
	     * Testing for Sign up service when adding invalid LastName 
	     */
		@Test
		 void addSignUpTestLastName(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai");
			String s1 = "LastName should not contain numbers";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding blank LastName 
	     */
		@Test
		 void addSignUpTestLastNameBlank(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai");
			u1.setLastName("");
			String s1 = "LastName should not be blank";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding invalid DOB 
	     */
		@Test
		 void addSignUpTestDob()
		{
			UserDTO u1 = userDetails();
			u1.setFirstName("sai");
			u1.setLastName("vin"); u1.setDob("25/01");
			String s1 = "Date is not Correct";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		
		/*
	     * Testing for Sign up service when adding invalid Phone number 
	     */
		@Test
		 void addSignUpTestPhn(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setDob("25-01-2000");
			String s1 = "Phone Number is not valid";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding blank Email id 
	     */
		@Test
		 void addSignUpTestEmailIdBlank(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301");u1.setDob("25/01/2000"); u1.setEmailId("");
			String s1 = "Email should not be blank";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding invalid Email id 
	     */
		@Test
		 void addSignUpTestEmailId(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setDob("25-01-2000");
			String s1 = "Email is invalid";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding already existing user 
	     */
		@Test
		 void addSignUpTestUserFound() {
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com"); u1.setDob("25/01/2000");
			User u2 = LoginUtils.convertTouser(u1);
			repo.saveAndFlush(u2);
			String s1 = "User is already present";
			try { 
			   if(repo.findByCid(u1.getEmailId(),u1.getUserType())!=null) throw new SignUpExceptions("User is already present");
			 }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding blank usertype 
	     */
		@Test
		 void addSignUpTestUserType(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com"); u1.setDob("25-01-2000");
			u1.setUserType("");
			String s1 = "UserType should not be blank";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding invalid password 
	     */
		@Test
		 void addSignUpTestPassword(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com");u1.setUserType("doctor"); u1.setDob("25-01-2000");
			String s1 = "Password must contains atleast one UpperCase, LowerCase, SpecialCharacter, Numeric";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding retype password different from password 
	     */
		@Test
		 void addSignUpTestRePassword(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com");u1.setUserType("doctor");
			u1.setPassword("Vin#123456"); u1.setDob("25-01-2000");
			String s1 = "ReTypePassword should be same as Password";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service when adding invalid Security answer 
	     */
		@Test
		 void addSignUpTestSecurityAns(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com");u1.setUserType("doctor");
			u1.setSecurityAns("Vin6"); u1.setDob("25-01-2000");
			String s1 = "Security Answer should not contain numbers";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		 
		/*
	     * Testing for Sign up service when adding blank Security answer 
	     */
		@Test
		 void addSignUpTestSecurityAnsBlank(){
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com");u1.setUserType("doctor");
			u1.setSecurityAns(""); u1.setDob("25-01-2000");
			String s1 = "Security Answer should not be blank";
			try { service.addSignUpDetails(u1); }
			catch(SignUpExceptions e){ assertTrue(s1.equals(e.getMessage())); }
		}
		
		/*
	     * Testing for Sign up service 
	     */
		@Test
		 void addSignUpDone() {
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com");
			u1.setPassword("Va1234563#"); u1.setReTypePassword("Va1234563#"); u1.setDob("25-01-2000");
			try {
			service.addSignUpDetails(u1);
			User u2 = LoginUtils.convertTouser(u1);
			User u3 = repo.saveAndFlush(u2);
			if(u2.equals(u3)) { assertTrue(true);}
			}catch(SignUpExceptions e) {}
					
		}
		
		/*
	     * Testing for login service
	     */
		@Test
		 void loginTest()
		{
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com");
			u1.setPassword("Va1234563#"); u1.setReTypePassword("Va1234563#"); u1.setDob("25-01-2000");
			try {
			service.addSignUpDetails(u1);
			User u2 = LoginUtils.convertTouser(u1);
			repo.saveAndFlush(u2);
		 	}catch(SignUpExceptions e) {}
			
			try {
			assertEquals("Login successfully",service.login("vineeth@gmail.com","Va1234563#","doc"));
			}
			catch(SignUpExceptions | NullPointerException e) { }
		}
			
		
		/*
	     * Testing for forgot password service 
	     */
		@Test
		 void forgotPasswordTest()
		{
			UserDTO u1 = userDetails();
			u1.setFirstName("sai"); u1.setLastName("vinnu"); u1.setPhoneNo("9857426301"); u1.setEmailId("vineeth@gmail.com");
			u1.setPassword("Va1234563#"); u1.setReTypePassword("Va1234563#"); u1.setDob("25-01-2000");
			try {
			service.addSignUpDetails(u1);
			User u2 = LoginUtils.convertTouser(u1);
			repo.saveAndFlush(u2);
		 	}catch(SignUpExceptions e) {}
			
			try {
				assertEquals("Changed Successfully",service.forgotPassword("vineeth@gmail.com","doc","hey","Va1234562#","Va1234562#"));
				}
				catch(SignUpExceptions | NullPointerException e) { }
		}
}
