package com.account.test;



import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.account.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest//(AccountControllerTest.class)
@AutoConfigureMockMvc
public class AccountControllerTest {
	@Autowired
    private MockMvc mvc;
	private  String accessToken;
	
	  @Test 
	  public void getHello() throws Exception {
		accessToken = obtainAccessToken("omkar", "omkar");
		mvc.perform(get("/hello").header("Authorization", "" + accessToken)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Hello!"));
	  }	  
	  
	  @Test
	  public void getAllAccountsAPI() throws Exception
	  {
		  accessToken = obtainAccessToken("omkar", "omkar");
		  	mvc.perform( MockMvcRequestBuilders
	        .get("/listAll").header("Authorization", "" + accessToken)
	        .accept(MediaType.APPLICATION_JSON))
	        .andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.accounts").exists())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.accounts[*].account_number").isNotEmpty());
	  }
	  
	  @Test
	  public void validateAllAccountsAPIhidden() throws Exception
	  {
		  accessToken = obtainAccessToken("omkar", "wongpass");
		  	mvc.perform( MockMvcRequestBuilders
	        .get("/listAll").header("Authorization", "" + accessToken)
	        .accept(MediaType.APPLICATION_JSON))
	        .andDo(print())
	        .andExpect(status().is(403));
	  }
	  @Test 
	  public void createAccountAPI() throws Exception 
	  { 
		  accessToken = obtainAccessToken("omkar", "omkar");
		  mvc.perform(  MockMvcRequestBuilders .put("/save").header("Authorization", "" + accessToken) 
	  .content(asJsonString(new Account(null, "firstName4", "lastName4",Long.parseLong("1234567889"),new Date(), "savings",BigDecimal.valueOf(1000.00))))
	  .contentType(MediaType.APPLICATION_JSON) 
	  .accept(MediaType.APPLICATION_JSON))
	  .andExpect(status().isOk())
	  .andExpect(MockMvcResultMatchers.jsonPath("$.account_number").exists()); }
	  
	  static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }	
	  private String obtainAccessToken(String username, String password) throws Exception {
		  
		    ResultActions result 
		      = mvc.perform(post("/login")
		    		  .content("{\"username\":\""+username+"\",\"password\":\""+password+"\"}")
		    		  .contentType(MediaType.APPLICATION_JSON) 	  
		        .with(httpBasic("fooClientIdPassword","secret"))
		        .accept("application/json;charset=UTF-8"));
		        //.andExpect(status().isOk()); //commented to check other status
		 
		    String resultString = result.andReturn().getResponse().getHeader("Authorization");
		 return resultString;
		}
}