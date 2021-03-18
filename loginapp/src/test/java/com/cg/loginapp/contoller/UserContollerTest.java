package com.cg.loginapp.contoller;

import static org.assertj.core.api.Assertions.assertThat; 
import static org.junit.Assert.assertEquals;


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

import com.cg.loginapp.model.UserDTO;
import com.cg.loginapp.service.UserServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Sai Veenith Neeli  
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserContoller.class)
class UserContollerTest {

	 @Autowired
	    private MockMvc mockMvc;
	    
	    @MockBean
	    private UserServices UserService;
	    /*
	     * Test the userSignup in user controller
	     */
	    @Test
	     void testUserSignUp() throws Exception,SignUpExceptions{
	        String URI = "/SignUp";
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
	  		
	       
	       Mockito.doNothing().when(UserService).addSignUpDetails(Mockito.any(UserDTO.class));
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat("Added Successfully").isEqualTo(jsonOutput);
	        assertEquals(202, mockHttpServletResponse.getStatus());
	     
	    }
	    
	    
	    private String converttoJsonUdto(Object userdto) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(userdto);
	    }

}
