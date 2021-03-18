package com.cg.loginapp.utils;
/**
 * @author Neelambari k  
 */
import java.util.ArrayList;
import java.util.List;

import com.cg.loginapp.entity.Admin;
import com.cg.loginapp.entity.User;
import com.cg.loginapp.model.UserDTO;


public class LoginUtils {
	
	private LoginUtils() {
		    throw new IllegalStateException("Utility class");
		  }

	/*
	 *  Convert UserDTO to User
	 */
	public static User convertTouser(UserDTO userdto) {
	       User user=new User();
	       user.setDob(userdto.getDob());
	       user.setEmailId(userdto.getEmailId());
	       user.setUserType(userdto.getUserType());
	       user.setPassword(userdto.getPassword());
	       user.setFirstName(userdto.getFirstName());
	       user.setLastName(userdto.getLastName());
	       user.setPhoneNo(userdto.getPhoneNo());
	       user.setSecurityQue(userdto.getSecurityQue());
	       user.setSecurityAns(userdto.getSecurityAns());
	       return user;
	}
	
	/*
	 *  Convert User to UserDTO
	 */
	public static UserDTO convertToUsetDTO(User user) {
	       UserDTO userdto=new UserDTO();
	       userdto.setDob(user.getDob());
	       userdto.setEmailId(user.getEmailId());
	       userdto.setUserType(user.getUserType());
	       userdto.setPassword(user.getPassword());
	       userdto.setFirstName(user.getFirstName());
	       userdto.setLastName(user.getLastName());
	       userdto.setPhoneNo(user.getPhoneNo());
	       userdto.setSecurityQue(user.getSecurityQue());
	       userdto.setSecurityAns(user.getSecurityAns());
	       return userdto;
	       
	       
	}
	
	/*
	 *  Convert UserDTO to Admin
	 */
	public static Admin convertToAdmin(UserDTO userdto) {
	       Admin admin=new Admin();
	       
	       admin.setAdminEmailId(userdto.getEmailId());
	       admin.setAdminPassword(userdto.getPassword());
	     
	       return admin;
	}
	
	/*
	 *  Convert List<User> to List<UserDTO>
	 */
	public static List<UserDTO> convertToUserDtoList(List<User> list){
		List<UserDTO> dtolist = new ArrayList<>();
		for(User user : list) 
			dtolist.add(convertToUsetDTO(user));
		return dtolist;
	}
	
}