package com.cg.loginapp.model;
/**
 * @author Neelambari k  
 */
import org.springframework.stereotype.Component;

@Component
public class LoginDTO {
	 private String emailId;
	 private String userType;
	 private String password;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
