package com.account.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.account.App;
import com.account.entity.Account;
import com.account.services.AccountService;

@RestController("/")
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(App.class);

	
	  @Autowired AccountService accountService;
	  
	  @GetMapping(value="hello")
	    public String getHello() {
		  logger.info("hello service called------------!");
	        return "Hello!";
	    }

	  @GetMapping(value="listAll")
	  public ResponseEntity<Map<String, Object>>
	  getAccount() {
		  
		  logger.info("Find All account service called------------!");
		  Map<String, Object> hmp=new HashMap<String, Object>();
		  hmp.put("accounts", accountService.findAll());
		  return ResponseEntity.ok(hmp); 
	  }
	  
	  @PutMapping(value="save") 
	  public ResponseEntity<Map<String, Object>> putAccount(@RequestBody Account act)
	  { 
		  logger.info("putAccount service called------------!");
		  act=accountService.save(act); 
		  Map<String, Object> hmp=new HashMap<String, Object>();
		  hmp.put("account_number", act.getAccount_number());
		  
		  return ResponseEntity.ok(hmp); 
		  }
	  
}
