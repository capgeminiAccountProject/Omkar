/*
 * package com.org.cap.controller;
 * 
 * import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 * 
 * import com.cap.controller.AccountRegistrationController;
 * 
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @WebMvcTest(AccountRegistrationController.class) public class
 * AccountRegistrationControllerTest {
 * 
 * 
 * @Autowired private MockMvc mvc;
 * 
 * 
 * }
 */
package com.org.cap.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cap.controller.AccountRegistrationController;
import com.cap.dto.Account;
import com.cap.dto.AccountRegistration;
import com.cap.dto.AccountRegistrationResponse;
import com.cap.repository.AccountCrudRepository;
import com.cap.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
public class AccountRegistrationControllerTest{
	 private MockMvc mvc;
		
	 	  @Mock 
	 	  private AccountService accountService;
	 	  @Mock 
	 	  private AccountCrudRepository accountCrudRepository;
	 	  
		  @InjectMocks 
		  private AccountRegistrationController accountController;
		
		  @Before 
		  public void init(){ 
			  MockitoAnnotations.initMocks(this); mvc =
			  MockMvcBuilders.standaloneSetup(accountController)  
			  .build(); 
			  }
		  	  
//All accounts start	
		  @Test 
		  public void getAllAccounts () throws Exception { List<Account>
			  accountList = new ArrayList<Account>(); 
			  Account act=new Account();
			  act.setAccount_num(1); 
			  act.setFirst_name("Omkar");act.setLast_name("Vedpathak");act.setSsn("123456798");
			  act.setDob("06-12-1919");act.setEmail("abc@test.com");act.setMobile_number("1234567890");
			  act.setHome_address("13 abc way, abccity abcstate 12345");
			  act.setMailing_address("13 abc way, abccity abcstate 12345");
			  act.setAccount_type("Savings");act.setMin_balance(100);
			  accountList.add(act); 
			  String result="success"; 
			  String token="abc"; 
			  when(accountService.isTokenValid(token)).thenReturn(result);
			  when(accountService.getAccounts()).thenReturn(accountList);
			  
			  mvc.perform(get("/account/accounts").header("Authorization", token))
			  .andExpect(status() .isOk())
			  .andExpect(MockMvcResultMatchers.jsonPath("$.accountList.[*].account_num").value(1));
		  }
	 
		  @Test
		  public void getAllAccountsInvalidToken() throws Exception
		  {
			  List<Account> accountList = new ArrayList<Account>();
			  String result="fail";
			  String token="abc";
			  when(accountService.isTokenValid(token)).thenReturn(result);
			  when(accountService.getAccounts()).thenReturn(accountList);

			  mvc.perform(get("/account/accounts").header("Authorization", token))
			  .andExpect(status()
			  .isOk())
			  .andExpect(MockMvcResultMatchers.jsonPath("$.exceptionDetails").value("Token cannot be validated"));
			  
		  }
		  
		  @Test
		  public void getAllAccountsNullPointer() throws Exception
		  {
			  String token="abc";
			  when(accountService.isTokenValid(token)).thenThrow(new NullPointerException());

			  mvc.perform(get("/account/accounts").header("Authorization", token))
			  .andExpect(status()
			  .isOk())
			  .andExpect(MockMvcResultMatchers.jsonPath("$.exceptionDetails").value("Runtime Exception Occurred: null"));
			  
			  
		  }
//All Accounts End
		
//Single account test start		  
		  @Test 
		  public void getAccountByAccountNum() throws Exception { List<Account>
			  accountList = new ArrayList<Account>(); 
			  Account act=new Account();
			  act.setAccount_num(1); 
			  act.setFirst_name("Omkar");act.setLast_name("Vedpathak");act.setSsn("123456798");
			  act.setDob("06-12-1919");act.setEmail("abc@test.com");act.setMobile_number("1234567890");
			  act.setHome_address("13 abc way, abccity abcstate 12345");
			  act.setMailing_address("13 abc way, abccity abcstate 12345");
			  act.setAccount_type("Savings");act.setMin_balance(100);
			  accountList.add(act); 
			  String result="success"; 
			  String token="abc"; 
			  when(accountService.isTokenValid(token)).thenReturn(result);
			 
			  when(accountService.getAccountByAccountNum(1)).thenReturn(act);
			  
			  mvc.perform(get("/account/accounts/1").header("Authorization", token))
			  .andExpect(status().isOk())
			  .andExpect(MockMvcResultMatchers.jsonPath("$.account.account_num").value(1));
		  }
//Single account test end
	
//delete account test start		  
		  @Test 
		  public void deleteAccount() throws Exception { List<Account>
			  accountList = new ArrayList<Account>(); 
			  Account act=new Account();
			  act.setAccount_num(1); 
			  act.setFirst_name("Omkar");act.setLast_name("Vedpathak");act.setSsn("123456798");
			  act.setDob("06-12-1919");act.setEmail("abc@test.com");act.setMobile_number("1234567890");
			  act.setHome_address("13 abc way, abccity abcstate 12345");
			  act.setMailing_address("13 abc way, abccity abcstate 12345");
			  act.setAccount_type("Savings");act.setMin_balance(100);
			  accountList.add(act); 
			  String result="success"; 
			  String token="abc"; 
			  when(accountService.isTokenValid(token)).thenReturn(result);
			  when(accountService.getAccountByAccountNum(act.getAccount_num())).thenReturn(act); 
			  doNothing().when(accountCrudRepository).delete(act);
			  
			  mvc.perform(get("/account/accounts/1").header("Authorization", token))
			  .andExpect(status().isOk())
			  .andDo(print())
			  .andExpect(MockMvcResultMatchers.jsonPath("$.account.account_num").value(1));
			  verify(accountService, times(1)).getAccountByAccountNum(act.getAccount_num());
		  }
//delete account test	end
		  
//Create account test Start		  
		  @Test 
		  public void createAccount() throws Exception { 
		  Account act=new Account();
			  act.setFirst_name("Omkar");act.setLast_name("Vedpathak");act.setSsn("123456798");
			  act.setDob("06-12-1919");act.setEmail("abc@test.com");act.setMobile_number("1234567890");
			  act.setHome_address("13 abc way, abccity abcstate 12345");
			  act.setMailing_address("13 abc way, abccity abcstate 12345");
			  act.setAccount_type("Savings");act.setMin_balance(100);
			  String result="success"; 
			  String token="abc"; 
			  act.setAccount_num(1);
			  
			  AccountRegistration actInput=new AccountRegistration();
			  actInput.setFirst_name("Omkar");actInput.setLast_name("Vedpathak");actInput.setSsn("123456798");
			  actInput.setDob("06-12-1919");actInput.setEmail("abc@test.com");actInput.setMobile_number("1234567890");
			  actInput.setHome_address("13 abc way, abccity abcstate 12345");
			  actInput.setMailing_address("13 abc way, abccity abcstate 12345");
			  actInput.setAccount_type("Savings");actInput.setMin_balance(100);
			  
			  AccountRegistrationResponse arsp=new AccountRegistrationResponse();
			  arsp.setAccount(act);
			  arsp.setSuccess(true);
			  arsp.setAccountNumber(String.valueOf(act.getAccount_num()));
			  when(accountService.isTokenValid(token)).thenReturn(result);
			  
		      when(accountService.createAccount( Matchers.<AccountRegistration>any() )).thenReturn(arsp);
			  
			  mvc.perform(post("/account/create").header("Authorization", token).content(asJsonString(actInput)).contentType(MediaType.APPLICATION_JSON))
			  
			  .andExpect(status().isOk())
			  .andDo(print())
			  .andExpect(MockMvcResultMatchers.jsonPath("$.account.account_num").value(act.getAccount_num()));
		  }
//create account test end		  
		  
		  static String asJsonString(final Object obj) {
		        try {
		            return new ObjectMapper().writeValueAsString(obj);
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }
		    }

}