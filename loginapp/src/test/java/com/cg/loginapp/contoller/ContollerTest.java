package com.cg.loginapp.contoller;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sai Veenith Neeli  
 */
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.loginapp.entity.User;
import com.cg.loginapp.model.UserDTO;
import com.cg.loginapp.service.AdminServices;
import com.cg.loginapp.service.UserServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminContoller.class)
class ContollerTest {

	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private AdminServices Service;
	    
	    @MockBean
	    private UserServices UserService;

	    /*
	     * Test the adminGetAllUser in admin controller
	     */
	    @Test
	      void testGetUsers() throws Exception{
	        String URI = "/admin/getAllUser";
	          
	        User u = new User();
		    u.setEmailId("vin@gmail.com");
			u.setUserType("doctor");
			u.setFirstName("sai");
			u.setLastName("vinnu");
			u.setPhoneNo("985623147");
			u.setPassword("sai#2123Saa");
			u.setDob("12-05-2000");
			u.setSecurityQue("whats your birth place");
			u.setSecurityAns("jgl");

	        User u1 = new User();
		    u1.setEmailId("vi22n@gmail.com");
			u1.setUserType("doctors");
			u1.setFirstName("saini");
			u1.setLastName("vinnu");
			u1.setPhoneNo("985623157");
			u1.setPassword("sai#2123Sa");
			u1.setDob("12-05-2001");
			u1.setSecurityQue("whats your birth place");
			u1.setSecurityAns("jglj");

	        List<User> UserList = new ArrayList<>();
	        UserList.add(u);
	        UserList.add(u1);

	        String jsonInput = this.converttoJson(UserList);

	        Mockito.when(Service.listAllUsers()).thenReturn(UserList);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
	    }
	    
	    /*
	     * Test the adminGetUserByEmailnUsertype in admin controller
	     */
	    @Test
	     void testGetUserByEmailnUsertype() throws Exception{
	        String URI= "/admin/getById/{emailId}/{userType}";
	        User u = new User();
	        u.setEmailId("vin@gmail.com");
	      		u.setUserType("doctor");
	      		u.setFirstName("sai");
	      		u.setLastName("vinnu");
	      		u.setPhoneNo("9856231471");
	      		u.setPassword("sai#2123Saa");
	      		u.setDob("12-05-2000");
	      		u.setSecurityQue("whats your birth place");
	      		u.setSecurityAns("jgl");
	        String jsonInput = this.converttoJson(u);

	        Mockito.when(Service.listUser(Mockito.any(),Mockito.any())).thenReturn(u);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,"vin@gmail.com","doctor")
	                .accept(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        System.out.println(jsonOutput);
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	    }
	    
	    /*
	     * Test the adminDeleteUserByEmailnUsertype in admin controller
	     */
	    @Test
	     void testDeleteUserByEmailnUsertype() throws Exception{
	        String URI = "/admin/deleteById/{emailId}/{userType}";
	        User u = new User();
	        u.setEmailId("vin@gmail.com");
	      		u.setUserType("doctor");
	      		u.setFirstName("sai");
	      		u.setLastName("vinnu");
	      		u.setPhoneNo("9856231471");
	      		u.setPassword("sai#2123Saa");
	      		u.setDob("12-05-2000");
	      		u.setSecurityQue("whats your birth place");
	      		u.setSecurityAns("jgl");

	        Mockito.when(Service.listUser(Mockito.any(),Mockito.any())).thenReturn(u);
	        Mockito.when(Service.deleteUser(Mockito.any(),Mockito.any())).thenReturn("Deleted successfully");
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI,"vin@gmail.com","doctor").accept(MediaType.
	        		APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

	        assertEquals(200, mockHttpServletResponse.getStatus());
	       
	    }
	    
	    /*
	     * Test the adminUpdate in admin controller
	     */
	    @Test
	     void testUpdate() throws SignUpExceptions,Exception{

	        String URI = "/admin/updateUsers/{emailId}/{userType}";
	       
	      		UserDTO udto = new UserDTO();
	      		udto.setEmailId("vin@gmail.com");
	      		udto.setUserType("doctor");
	      		udto.setFirstName("sai");
	      		udto.setLastName("vinnu");
	      		udto.setPhoneNo("9856231471");
	      		udto.setPassword("sai#2123Saa");
	      		udto.setDob("12-05-2000");
	      		udto.setSecurityQue("whats your birth place");
	      		udto.setSecurityAns("jgl");
	        String jsonInput = this.converttoJsonUdto(udto);

	        Mockito.when(Service.updateUser(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn("{\"emailId\":\"vin@gmail.com\",\"userType\":\"doctor\",\"firstName\":\"sai\",\"lastName\":\"vinnu\",\"dob\":\"12-05-2000\",\"phoneNo\":\"9856231471\",\"password\":\"sai#2123Saa\",\"reTypePassword\":null,\"securityQue\":\"whats your birth place\",\"securityAns\":\"jgl\"}");
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,"vin@gmail.com","doctor","saii").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
	    }
	    
	    /*
	     * Test the adminAddUser in admin controller
	     */
	    @Test
	     void testAdminAddUser() throws Exception,SignUpExceptions{
	        String URI = "/admin/addUser";
	        UserDTO udto = new UserDTO();
	  		udto.setEmailId("vin@gmail.com");
	  		udto.setUserType("doctor");
	  		udto.setFirstName("sai");
	  		udto.setLastName("vinnu");
	  		udto.setPhoneNo("9856231471");
	  		udto.setPassword("sai#2123Saa");
	  		udto.setDob("12-05-2000");
	  		udto.setSecurityQue("whats your birth place");
	  		udto.setSecurityAns("jgl");
	  		String jsonInput = this.converttoJsonUdto(udto);
	        Mockito.when(Service.addUser(Mockito.any(UserDTO.class))).thenReturn("{\"emailId\":\"vin@gmail.com\",\"userType\":\"doctor\",\"firstName\":\"sai\",\"lastName\":\"vinnu\",\"dob\":\"12-05-2000\",\"phoneNo\":\"9856231471\",\"password\":\"sai#2123Saa\",\"reTypePassword\":null,\"securityQue\":\"whats your birth place\",\"securityAns\":\"jgl\"}");
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        assertEquals(202,mockHttpServletResponse.getStatus());
	      
	    }
	    
	    /*
	     * Test the userAddUser in admin controller
	     */
	    @Test
	    void testAddUser() throws Exception,SignUpExceptions{
	        String URI ="/admin/{emailId}/{password}/";
	        UserDTO udto = new UserDTO();
	  		udto.setEmailId("vin@gmail.com");
	  		udto.setUserType("admin");
	  		udto.setFirstName("sai");
	  		udto.setLastName("vinnu");
	  		udto.setPhoneNo("9856231471");
	  		udto.setPassword("sai#2123Saa");
	  		udto.setDob("12-05-2000");
	  		udto.setSecurityQue("whats your birth place");
	  		udto.setSecurityAns("jgl");
	  		String jsonInput = this.converttoJsonUdto(udto);
	        Mockito.when(UserService.adminlogin(Mockito.any(),Mockito.any())).thenReturn("{\"emailId\":\"vin@gmail.com\",\"userType\":\"admin\",\"firstName\":\"sai\",\"lastName\":\"vinnu\",\"dob\":\"12-05-2000\",\"phoneNo\":\"9856231471\",\"password\":\"sai#2123Saa\",\"reTypePassword\":null,\"securityQue\":\"whats your birth place\",\"securityAns\":\"jgl\"}");
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI,"vin@gmail.com","sai#2123Saa").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        assertEquals(202, mockHttpServletResponse.getStatus());
	     
	    }
	    
	    private String converttoJsonUdto(Object userdto) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(userdto);
	    }
	    
	    private String converttoJson(Object user) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(user);
	    }


}
