package com.cg.loginapp.service;

/**
 * @author Sai Veenith Neeli  
 */


import java.util.List; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.loginapp.contoller.SignUpExceptions;
import com.cg.loginapp.entity.Admin;
import com.cg.loginapp.entity.User;
import com.cg.loginapp.model.UserDTO;
import com.cg.loginapp.repository.AdminRepository;
import com.cg.loginapp.repository.LoginRepository;
import com.cg.loginapp.utils.LoginUtils;

 
@Service
public class UserServices {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);
	
	@Autowired
	LoginRepository repo;
	
	@Autowired
	AdminRepository adminRepo;
	
	Pattern pEmail = Pattern.compile("^(.+)@(.+)$");
	Pattern pString = Pattern.compile("[a-zA-Z]*");
	Pattern pDob= Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((?:19|20)[0-9][0-9])$");
	
	/*
	 *  Login service
	 */
	public String login(String emailId,String password,String userType) throws SignUpExceptions,NullPointerException{
		logger.info("Login service in User service is accessed");
		User appuser=repo.findByCid(emailId,userType);
       
		try {
		if(appuser.getPassword().equals(password))
		{
			return "Login successfully";
		}
		}
		catch(NullPointerException e)
		{
			throw new SignUpExceptions("Login Unsuccessful .. Invalid Email or UserType ");	 
		}
		
		return "Login UnSuccessful . . Password is not correct ";
	}
	
	/*
	 *  Password validation service
	 */
	public static boolean passwordIsValid(String password) {
		
		logger.info("Password validation service in User service is accessed");
		
	    Pattern specialCharPatten = Pattern.compile("[~!@#$%^&*_-]");
	    Pattern upperCasePatten = Pattern.compile("[A-Z ]");
	    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
	    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
	    
	    boolean flag=false;

	    if (password.length() >= 8 && specialCharPatten.matcher(password).find() && 
	    	upperCasePatten.matcher(password).find() && lowerCasePatten.matcher(password).find() && 
	    		digitCasePatten.matcher(password).find()) {
	        flag=true;
	    }

	    return flag;

	}
	
	/*
	 *  Date of birth validation service
	 */
      public boolean dobIsValid(String date) {

    	  logger.info("Date of birth validation service in User service is accessed");
    	  
        boolean result = false;

       
        Matcher mDob = pDob.matcher(date);
        if (mDob.matches()) {

            result = true;

           
            int year = Integer.parseInt(mDob.group(3));
            String month = mDob.group(2);
            String day = mDob.group(1);
            
            if ((month.equals("4") || month.equals("6") || month.equals("9") ||
                    month.equals("04") || month.equals("06") || month.equals("09") ||
                    month.equals("11")) && day.equals("31")) {
                result = false;
            } else if (month.equals("2") || month.equals("02")) {
                if (day.equals("30") || day.equals("31")) {
                    result = false;
                } else if (day.equals("29")) {  // leap year? feb 29 days.
                    if (!isLeapYear(year)) {
                        result = false;
                    }
                }
            }

        }

        return result;
    }
	
     /*
  	 *  check leap year 
  	 */
	private boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
	
	/*
	 *  Sign up Validation service
	 */
	public boolean validation(UserDTO udto) throws SignUpExceptions
	{
		
		logger.info("Sign up validation service in User service is accessed");
		
		if(udto.getEmailId()==null||udto.getEmailId().isBlank()) throw new SignUpExceptions("Email should not be blank");
		
		else if(udto.getFirstName()==null||udto.getFirstName().isBlank()) throw new SignUpExceptions("FirstName should not be blank"); 
		
		else if(udto.getLastName()==null||udto.getLastName().isBlank()) throw new SignUpExceptions("LastName should not be blank"); 
		
		else if(udto.getSecurityAns()==null||udto.getSecurityAns().isBlank()) throw new SignUpExceptions("Security Answer should not be blank"); 
		
		Matcher mEmail = pEmail.matcher(udto.getEmailId());
		Matcher mFirstName = pString.matcher(udto.getFirstName()); 
		Matcher mLastName = pString.matcher(udto.getLastName());
		Matcher mSecurityAns = pString.matcher(udto.getSecurityAns());
		
		
		if(udto.getFirstName().isBlank()) throw new SignUpExceptions("FirstName should not be blank");
		
		else if(!mFirstName.matches()) throw new SignUpExceptions("FirstName should not contain numbers");
		
		else if(udto.getLastName().isBlank()) throw new SignUpExceptions("LastName should not be blank");
		
		else if(!mLastName.matches()) throw new SignUpExceptions("LastName should not contain numbers");
		
		else if(!dobIsValid(udto.getDob())) throw new SignUpExceptions("Date is not Correct");
		
		else if(udto.getPhoneNo().length() != 10) throw new SignUpExceptions("Phone Number is not valid");
		
		else if(udto.getEmailId().isBlank()) throw new SignUpExceptions("Email should not be blank");
		
		else if(!mEmail.matches()) throw new SignUpExceptions("Email is invalid");
		
		else if(udto.getUserType().isBlank()) throw new SignUpExceptions("UserType should not be blank");
		
		else if(!mSecurityAns.matches()) throw new SignUpExceptions("Security Answer should not contain numbers");
		
		else if(udto.getSecurityAns().isBlank()) throw new SignUpExceptions("Security Answer should not be blank");
		
		else if(!passwordIsValid(udto.getPassword())) throw new SignUpExceptions("Password must contains atleast one UpperCase, LowerCase, SpecialCharacter, Numeric");
		
		else if(!udto.getPassword().equals(udto.getReTypePassword())) throw new SignUpExceptions("ReTypePassword should be same as Password");
		
		return true;
	}
	
	public boolean idIsValid(UserDTO udto) throws SignUpExceptions
	{
		if(repo.findByCid(udto.getEmailId(),udto.getUserType())!=null)
		{
			throw new SignUpExceptions("User is already present");
		}
		
		return false;
	}
	
	/*
	 *  Sign up service
	 */
	public void addSignUpDetails(UserDTO udto) throws SignUpExceptions
	{
		logger.info("Sign up service in User service is accessed");
		if(validation(udto) && !idIsValid(udto))
		{
			if((udto.getUserType().equals("Admin")&&udto.getPassword().equals("Admin@123")))
			{
				Admin admin = LoginUtils.convertToAdmin(udto);
				
				adminRepo.saveAndFlush(admin);
			}
			else {
			User u = LoginUtils.convertTouser(udto);
			
			repo.saveAndFlush(u);
			}
			
		}
		
	}
	
	/*
	 *  Forgot password service
	 */
	public String forgotPassword(String emailId , String userType , String securityAns , String newPassword , String reTypePassword) throws SignUpExceptions,NullPointerException
	{
		logger.info("Forgot password service in User service is accessed");
		User u = repo.findByCid(emailId, userType);
		
		try { 
			if(!passwordIsValid(newPassword) || !u.getSecurityAns().equals(securityAns) || !newPassword.equals(reTypePassword))
			{
				throw new SignUpExceptions("Security Answer is Incorrect or Enter valid Password");
			}
			else
			{
				u.setPassword(newPassword);
				repo.saveAndFlush(u);
			}
			}
			catch(NullPointerException e)
			{
				throw new SignUpExceptions("User is Not found");
			}
		
		return "Changed Successfully";
	}
	
	/*
	 *  List all users service
	 */
	public List<User> getDetails()
	{
		logger.info("List getdetails service in User service is accessed");
		return repo.findAll();
	}
	
	/*
	 *  Admin Login service
	 */
	public String adminlogin(String emailId,String password) throws SignUpExceptions,NullPointerException{
		logger.info("Admin Login service in User service is accessed");
		
       
		try {
		Admin appuser=adminRepo.findById(emailId).get();
		if(appuser.getAdminPassword().equals(password))
		{
			return "Login successfully";
		}
		}
		catch(NullPointerException e)
		{
			throw new SignUpExceptions("Login Unsuccessful .. Invalid Password or Email ");	 
		}
		
		return "UnSuccessful";
	}
	
}

